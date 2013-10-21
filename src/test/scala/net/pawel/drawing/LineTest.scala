package net.pawel.drawing

import org.specs2.matcher.JUnitMustMatchers
import org.junit.Test
import net.pawel.drawing.tools._
import net.pawel.drawing.tools.Canvas
import net.pawel.drawing.tools.Point
import scala.Some

class LineTest extends JUnitMustMatchers {

  val canvas = Canvas(5, 5)

  @Test
  def horizontal_line {
    drawAndPrint(Line(2, 2, 5, 2)) must beEqualTo(
      """/-------
         /|     |
         /| xxxx|
         /|     |
         /|     |
         /|     |
         /-------
      """.stripMargin('/').trim
    )
  }

  @Test
  def horizontal_line_with_inverted_points {
    drawAndPrint(Line(5, 2, 2, 2)) must beEqualTo(
      """/-------
         /|     |
         /| xxxx|
         /|     |
         /|     |
         /|     |
         /-------
      """.stripMargin('/').trim
    )
  }

  @Test
  def vertical_line {
    drawAndPrint(Line(2, 2, 2, 5)) must beEqualTo(
      """/-------
         /|     |
         /| x   |
         /| x   |
         /| x   |
         /| x   |
         /-------
      """.stripMargin('/').trim
    )
  }

  @Test
  def vertical_line_with_inverted_points {
    drawAndPrint(Line(2, 5, 2, 2)) must beEqualTo(
      """/-------
         /|     |
         /| x   |
         /| x   |
         /| x   |
         /| x   |
         /-------
      """.stripMargin('/').trim
    )
  }

  def drawAndPrint(drawing: Drawing) = PrintCanvas(canvas.draw(drawing))

  def queryCanvas(point: Point) = Some(' ')
}
