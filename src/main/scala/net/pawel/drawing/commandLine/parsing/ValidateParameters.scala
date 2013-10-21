package net.pawel.drawing.commandLine.parsing

import scala.util.control.NonFatal
import net.pawel.drawing.commandLine.parsing.ValidateParameterTypes.SingleParameterValidator

trait ValidateParameters {
  def apply(parameters: List[String]): Option[String]
}

class ValidateParameterTypes(val validators: List[SingleParameterValidator]) extends ValidateParameters {
  private val expectedSize = validators.size

  def apply(parameters: List[String]): Option[String] = {
    if (parameters.size != expectedSize) {
      Failure(s"Required $expectedSize parameters, got ${parameters.mkString(",")}")
    } else {
      applyValidators(parameters)
    }
  }

  private def applyValidators(parameters: List[String]) = {
    val failures = validators
      .zip(parameters)
      .flatMap({
        case (validate, parameter) => validate(parameter)
      })
    failures.headOption.map(_ => failures.mkString("\n"))
  }
}

object ValidateParameterTypes {
  type SingleParameterValidator = String => Option[String]

  def apply(validators: SingleParameterValidator*): ValidateParameterTypes = new ValidateParameterTypes(validators.toList)

  def validateInt(string: String): Option[String] =
    try {
      string.toInt;
      Success()
    } catch {
      case NonFatal(e) => Failure(s"This parameter is not an integer: $string")
    }

  def validateColour(string: String): Option[String] =
    if (string.size != 1) {
      Failure(s"Expected single character as colour, got $string")
    } else {
      Success()
    }

  def repeat[T](n: Int, validate: T) =
    (1 to n).toList.map(_ => validate)
}

object Success {
  def apply() = None
}

object Failure {
  def apply(message: String): Option[String] = Some(message)
}

