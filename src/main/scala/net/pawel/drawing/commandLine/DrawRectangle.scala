package net.pawel.drawing.commandLine

import net.pawel.drawing.{Canvas, Rectangle}
import ParameterTypeValidator._

object DrawRectangle extends CommandParser {
  val commandCharacter: Char = 'R'

  val validator = new ParameterTypeValidator(repeat(4, validateInt _))

  def create(parameters: List[String]) = parameters.map(_.toInt) match {
    case List(x1, y1, x2, y2) => canvas => canvas.map(_.draw(Rectangle(x1, y1, x2, y2)))
  }
}
