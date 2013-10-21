package net.pawel.drawing

import org.specs2.matcher.JUnitMustMatchers
import org.junit.Test
import net.pawel.drawing.tools.{PrintCanvas, Line, Canvas}

class CanvasTest extends JUnitMustMatchers {

  @Test
  def querying_empty_canvas_returns_null_colour {
    val canvas = Canvas(2, 3)
    canvas.points.foreach(
      canvas.query(_) must beSome(' ')
    )
  }

  @Test
  def querying_a_canvas_with_a_line {
    val line = Line(1, 1, 2, 1)
    val linePoints = line.draw(_ => None).map(_.point)

    val canvas = Canvas(2, 3).draw(line)
    val pointsNotOnTheLine = canvas.points.diff(linePoints)

    pointsNotOnTheLine.foreach(point =>
      canvas.query(point) must beSome(' ')
    )

    linePoints.foreach(point =>
      canvas.query(point) must beSome(Line.Colour)
    )
  }

  @Test
  def querying_outside_of_canvas_returns_none {
    val canvas = Canvas(2, 3)
    canvas.query(0, 0) must beNone
    canvas.query(1, 0) must beNone
    canvas.query(1, 4) must beNone
    canvas.query(0, 1) must beNone
    canvas.query(4, 1) must beNone
    canvas.query(9, 9) must beNone
  }

  @Test
  def printing {
    val line = Line(1, 1, 2, 1)
    PrintCanvas(Canvas(2, 3).draw(line)) must beEqualTo(
      """/----
         /|xx|
         /|  |
         /|  |
         /----
      """.stripMargin('/').trim)
  }
}
