package net.pawel.drawing.commandLine.parsing

import ValidateParameterTypes._
import net.pawel.drawing.tools.Rectangle
import net.pawel.drawing.commands.Draw

object DrawRectangle extends ValidateAndParseCommand(
  commandCharacter = 'R',
  validate = new ValidateParameterTypes(repeat(4, validateInt _)),
  parse = {
    case List(x1, y1, x2, y2) => Draw(Rectangle(x1.toInt, y1.toInt, x2.toInt, y2.toInt))
  }
)
