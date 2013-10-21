package net.pawel.drawing.commandLine

import net.pawel.drawing.{PrintCanvas, Canvas}

object DrawingApplication {

  private val commands = List(CreateCanvas, BucketFill, DrawLine, DrawRectangle, Quit)

  def main(params: Array[String]) = programLoop()

  def programLoop(canvas: Option[Canvas] = None,
                  canvasHasBeenCreated: Boolean = false,
                  reader: Reader = ConsoleReader,
                  writer: Writer = ConsoleWriter) {
    writer.write("enter command: ")
    val line = reader.readLine
    val nextCanvas = parseAndTransform(line) match {
      case Left(Failure(message)) => {
        writer.write(message + "\n")
        canvas
      }
      case Right(transform) => {
        val newCanvas = transform(canvas)
        writer.write(newCanvas.map(PrintCanvas(_)).getOrElse("") + "\n")
        newCanvas
      }
    }

    if (!canvasHasBeenCreated || nextCanvas.isDefined) {
      programLoop(nextCanvas, canvasHasBeenCreated || nextCanvas.isDefined, reader, writer)
    }
  }

  private def parseAndTransform(line: String) = {
    val parameters = line.split(" ")

    if (notEmpty(parameters)) {
      Left(Failure("Please enter a command"))
    } else {
      val commandCharacter = parameters.head.head
      val commandParser = findCommandFor(commandCharacter)
      val inputParameters = parameters.tail.toList
      commandParser
        .map(_.parse(inputParameters))
        .getOrElse(Left(Failure(s"Unknown command $line")))
    }
  }


  def notEmpty(parameters: Array[String]): Boolean = {
    parameters.isEmpty || parameters.head.isEmpty
  }

  private def findCommandFor(commandCharacter: Char) =
    commands.find(_.commandCharacter == commandCharacter)
}

trait Reader {
  def readLine: String
}

trait Writer {
  def write(line: String)
}

object ConsoleReader extends Reader {
  def readLine = Predef.readLine
}

object ConsoleWriter extends Writer {
  def write(line: String) = print(line)
}
