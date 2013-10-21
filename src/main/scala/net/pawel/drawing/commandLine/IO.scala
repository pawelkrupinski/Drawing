package net.pawel.drawing.commandLine

trait Reader {
  def readLine: String
}

trait Writer {
  def write(line: String)
  def writeLine(line: String) = write(line + "\n")
}

object ConsoleReader extends Reader {
  def readLine = Predef.readLine
}

object ConsoleWriter extends Writer {
  def write(line: String) = print(line)
}