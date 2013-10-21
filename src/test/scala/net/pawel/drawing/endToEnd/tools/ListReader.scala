package net.pawel.drawing.endToEnd.tools

import net.pawel.drawing.commandLine.Reader

case class ListReader(list: List[String]) extends Reader {
  var remainingLines = list

  def readLine: String = {
    val result = remainingLines.head
    remainingLines = remainingLines.tail
    result
  }
}
