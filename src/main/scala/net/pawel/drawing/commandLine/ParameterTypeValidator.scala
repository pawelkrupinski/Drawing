package net.pawel.drawing.commandLine

import scala.util.control.NonFatal
import net.pawel.drawing.commandLine.ParameterTypeValidator.SingleParameterValidator


trait ParametersValidator {
  def apply(parameters: List[String]): List[Failure]
}

class ParameterTypeValidator(val validators: List[SingleParameterValidator]) extends ParametersValidator {
  val expectedSize = validators.size

  def apply(parameters: List[String]): List[Failure] = {
    if (parameters.size != expectedSize) {
      List(Failure(s"Required $expectedSize parameters, got ${parameters.mkString(",")}"))
    } else {
      validateAll(parameters)
    }
  }

  protected def validateAll(parameters: List[String]) =
    validators
      .zip(parameters)
      .flatMap({
      case (validate, parameter) => validate(parameter)
    })
}

object ParameterTypeValidator {
  type SingleParameterValidator = String => Option[Failure]

  def apply(validators: SingleParameterValidator*): ParameterTypeValidator = new ParameterTypeValidator(validators.toList)

  def validateInt(string: String): Option[Failure] =
    try {
      string.toInt; None
    } catch {
      case NonFatal(e) => Some(Failure(s"This parameter is not an integer: $string"))
    }

  def validateColour(string: String): Option[Failure] =
    if (string.size != 1) {
      Some(Failure(s"Expected single character as colour, got $string"))
    } else {
      None
    }

  def repeat[T](n: Int, validate: T) =
    (1 to n).toList.map(_ => validate)
}

