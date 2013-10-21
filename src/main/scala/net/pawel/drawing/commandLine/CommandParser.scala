package net.pawel.drawing.commandLine

import net.pawel.drawing.Canvas

case class Failure(message: String)

trait CommandParser {

  type CanvasTransform = Option[Canvas] => Option[Canvas]

  val commandCharacter: Char

  protected val validator: ParametersValidator

  protected def create(parameters: List[String]): CanvasTransform

  def parse(parameters: List[String]): Either[Failure, CanvasTransform] = {
    val failures = validator.apply(parameters)

    if (!failures.isEmpty) {
      val message = failures.map(_.message).mkString("Errors found when parsing the parameters: \n", "\n", "")
      Left(Failure(message))
    } else {
      Right(create(parameters))
    }
  }
}