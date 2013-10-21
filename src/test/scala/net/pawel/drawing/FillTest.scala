package net.pawel.drawing

import org.specs2.matcher.JUnitMustMatchers
import org.junit.Test
import net.pawel.drawing.tools._
import Pixel.Colour
import net.pawel.drawing.tools.Canvas

class FillTest extends JUnitMustMatchers {

  @Test
  def filling_an_empty_canvas {
    val canvas = Canvas(3, 3).draw(Fill(Point(2, 2), 'o'))
    PrintCanvas(canvas) must beEqualTo(
      """/-----
         /|ooo|
         /|ooo|
         /|ooo|
         /-----
      """.stripMargin('/').trim)
  }

  @Test
  def filling_a_canvas_with_an_existing_drawing {
    val canvas = Canvas(5, 5).draw(Line(2, 3, 4, 3)).draw(Fill(Point(1, 1), 'o'))
    PrintCanvas(canvas) must beEqualTo(
      """/-------
         /|ooooo|
         /|ooooo|
         /|oxxxo|
         /|ooooo|
         /|ooooo|
         /-------
      """.stripMargin('/').trim)
  }

  @Test
  def line_is_blocking_half_a_canvas {
    val canvas = Canvas(5, 5).draw(Line(1, 3, 5, 3)).draw(Fill(Point(1, 1), 'o'))
    PrintCanvas(canvas) must beEqualTo(
      """/-------
         /|ooooo|
         /|ooooo|
         /|xxxxx|
         /|     |
         /|     |
         /-------
      """.stripMargin('/').trim)
  }

  @Test
  def filling_outside_of_a_rectangle {
    val canvas = Canvas(5, 5).draw(Rectangle(2, 2, 4, 4)).draw(Fill(Point(1, 1), 'o'))
    PrintCanvas(canvas) must beEqualTo(
      """/-------
         /|ooooo|
         /|oxxxo|
         /|ox xo|
         /|oxxxo|
         /|ooooo|
         /-------
      """.stripMargin('/').trim)
  }

  @Test
  def filling_inside_of_a_rectangle {
    val canvas = Canvas(5, 5).draw(Rectangle(1, 1, 4, 4)).draw(Fill(Point(2, 2), 'o'))
    PrintCanvas(canvas) must beEqualTo(
      """/-------
         /|xxxx |
         /|xoox |
         /|xoox |
         /|xxxx |
         /|     |
         /-------
      """.stripMargin('/').trim)
  }

  @Test
  def filling_the_border_of_a_rectangle {
    val canvas = Canvas(5, 5).draw(Rectangle(1, 1, 4, 4)).draw(Fill(Point(1, 1), 'o'))
    PrintCanvas(canvas) must beEqualTo(
      """/-------
         /|oooo |
         /|o  o |
         /|o  o |
         /|oooo |
         /|     |
         /-------
      """.stripMargin('/').trim)
  }
}
