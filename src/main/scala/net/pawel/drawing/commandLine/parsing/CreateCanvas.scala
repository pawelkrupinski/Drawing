package net.pawel.drawing.commandLine.parsing

import ValidateParameterTypes._
import net.pawel.drawing.tools.Canvas
import net.pawel.drawing.commands.NewCanvas

object CreateCanvas extends ValidateAndParseCommand(
  commandCharacter = 'C',
  validate = new ValidateParameterTypes(repeat(2, validateInt _)),
  parse = {
    case List(width, height) => NewCanvas(Canvas(width.toInt, height.toInt))
  }
)