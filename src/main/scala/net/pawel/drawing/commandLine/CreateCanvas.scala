package net.pawel.drawing.commandLine

import net.pawel.drawing.Canvas
import scala.Some
import ParameterTypeValidator._

object CreateCanvas extends CommandParser {
  val commandCharacter = 'C'

  val validator = new ParameterTypeValidator(repeat(2, validateInt _))

  def create(parameters: List[String]) = parameters.map(_.toInt) match {
    case List(width, height) => createCommand(width, height)
  }

  private def createCommand(width: Int, height: Int): CanvasTransform = _ => Some(Canvas(width, height))
}