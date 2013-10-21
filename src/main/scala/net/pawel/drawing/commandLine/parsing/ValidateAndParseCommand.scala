package net.pawel.drawing.commandLine.parsing

import net.pawel.drawing.commands.{Error, Command}

class ValidateAndParseCommand(val commandCharacter: Character,
                              val validate: ValidateParameters,
                              val parse: List[String] => Command) {

  def apply(parameters: List[String]): Command =
    validate(parameters)
      .map(toError)
      .getOrElse(parse(parameters))

  def toError(message: String) = Error("Errors found when parsing the parameters: \n" + message)
}


