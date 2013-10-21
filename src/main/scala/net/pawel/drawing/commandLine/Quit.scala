package net.pawel.drawing.commandLine

object Quit extends CommandParser {
  val commandCharacter: Char = 'Q'

  val validator = new ParametersValidator { def apply(parameters: List[String]): List[Failure] = Nil }

  def create(parameters: List[String]) = _ => None
}
