package net.pawel.drawing.commandLine.parsing

import net.pawel.drawing.commands.Error
import scala.Error

object CommandParsers {
  private val parsers = List(CreateCanvas, BucketFill, DrawLine, DrawRectangle, Quit)

  def commandFor(line: String) = {
    val parameters = line.split(" ")

    if (notEmpty(parameters)) {
      Error("Please enter a command")
    } else {
      val commandCharacter = parameters.head.head
      val validateAndParse = findParserFor(commandCharacter)
      val inputParameters = parameters.tail.toList
      validateAndParse.map(_.apply(inputParameters))
        .getOrElse(Error(s"Unknown command $line"))
    }
  }

  private def notEmpty(parameters: Array[String]): Boolean = {
    parameters.isEmpty || parameters.head.isEmpty
  }

  private def findParserFor(commandCharacter: Char) = parsers.find(_.commandCharacter == commandCharacter)
}
