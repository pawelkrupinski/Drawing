package net.pawel.drawing.endToEnd.tools

import org.specs2.matcher.JUnitMustMatchers
import scala.io.Source
import net.pawel.drawing.commandLine.DrawingApplication

case class Scenario(file: String) extends JUnitMustMatchers {
  lazy val scenarioLines = Source.fromInputStream(getClass.getResourceAsStream(s"/scenarios/$file")).getLines().toList

  val enterCommandString = "enter command: "

  def run() {
    val lines = scenarioLines.map(line =>
      if (line.startsWith(enterCommandString)) {
        (enterCommandString, Some(line.substring(enterCommandString.length)))
      } else {
        (line, None)
      })
    val expectedOutput = lines.map(_._1).mkString("\n").trim
    val input = lines.flatMap(_._2)
    val writer = new RecordingWriter
    DrawingApplication.programLoop(writer = writer, reader = new ListReader(input))

    writer.linesContent.trim must beEqualTo(expectedOutput)
  }
}
