package net.pawel.drawing.endToEnd.tools

import net.pawel.drawing.commandLine.Writer

class RecordingWriter extends Writer {
  private var lines = Vector[String]()

  def linesContent = lines.mkString("\n")

  def write(line: String) =
    lines ++= Vector(line)
}
