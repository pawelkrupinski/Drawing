package net.pawel.drawing.commandLine

import net.pawel.drawing.{Point, Fill, Canvas}
import net.pawel.drawing.Pixel.Colour
import ParameterTypeValidator._

object BucketFill extends CommandParser {
  val commandCharacter: Char = 'B'

  val validator = ParameterTypeValidator(validateInt _, validateInt _, validateColour _)

  def create(parameters: List[String]) = parameters match {
    case List(x, y, colour) => canvas => canvas.map(fillCommand(x.toInt, y.toInt, colour.head))
  }

  private def fillCommand(x: Int, y: Int, colour: Colour)(canvas: Canvas) =
    canvas.draw(Fill(Point(x, y), colour))
}
