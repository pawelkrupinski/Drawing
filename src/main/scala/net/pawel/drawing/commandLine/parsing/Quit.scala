package net.pawel.drawing.commandLine.parsing

import net.pawel.drawing.commands.QuitProgram

object Quit extends ValidateAndParseCommand(
  commandCharacter = 'Q',
  validate = new ValidateParameterTypes(Nil),
  parse = _ => QuitProgram
)
