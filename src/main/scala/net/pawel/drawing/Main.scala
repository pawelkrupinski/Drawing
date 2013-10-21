package net.pawel.drawing

import net.pawel.drawing.commands._
import net.pawel.drawing.commandLine.parsing._
import net.pawel.drawing.commandLine.{ConsoleWriter, Writer, ConsoleReader, Reader}
import net.pawel.drawing.tools.{PrintCanvas, Canvas}
import net.pawel.drawing.commands.{QuitProgram, Error, Draw}

object Main {

  def main(params: Array[String]): Unit = programLoop()

  def programLoop(canvas: Option[Canvas] = None,
                  reader: Reader = ConsoleReader,
                  writer: Writer = ConsoleWriter): Unit = {
    writer.write("enter command: ")
    val line = reader.readLine

    val command = CommandParsers.commandFor(line)

    if (command == QuitProgram) {
      ()
    } else {
      val nextCanvas = command match {
        case draw: Draw => canvas.map(draw)
        case NewCanvas(canvas) => Some(canvas)
        case Error(message) => { writer.writeLine(message); None }
      }
      nextCanvas.foreach(printCanvasWith(writer))
      programLoop(nextCanvas.orElse(canvas), reader, writer)
    }
  }

  def printCanvasWith(writer: Writer)(canvas: Canvas) = writer.writeLine(PrintCanvas(canvas))
}
