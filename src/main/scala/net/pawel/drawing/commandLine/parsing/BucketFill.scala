package net.pawel.drawing.commandLine.parsing

import ValidateParameterTypes._
import net.pawel.drawing.tools.{Point, Fill}
import net.pawel.drawing.commands.Draw

object BucketFill extends ValidateAndParseCommand(
  commandCharacter = 'B',
  validate = ValidateParameterTypes(validateInt _, validateInt _, validateColour _),
  parse = {
    case List(x, y, colour) => Draw(Fill(Point(x.toInt, y.toInt), colour.head))
  }
)
