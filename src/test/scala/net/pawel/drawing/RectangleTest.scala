package net.pawel.drawing

import org.specs2.matcher.JUnitMustMatchers
import org.junit.Test
import scala.collection.immutable.TreeSet

class RectangleTest extends JUnitMustMatchers {

  val canvas = Canvas(5, 5)

  @Test
  def drawing_a_rectangle {
    PrintCanvas(canvas.draw(Rectangle(3, 2, 5, 4))) must beEqualTo(
      """/-------
         /|     |
         /|  xxx|
         /|  x x|
         /|  xxx|
         /|     |
         /-------
      """.stripMargin('/').trim
    )
  }

  @Test
  def one_pixel_thin {
    PrintCanvas(canvas.draw(Rectangle(3, 1, 5, 1))) must beEqualTo(
      """/-------
         /|  xxx|
         /|     |
         /|     |
         /|     |
         /|     |
         /-------
      """.stripMargin('/').trim
    )
  }

  @Test
  def one_pixel_rectangle {
    PrintCanvas(canvas.draw(Rectangle(3, 1, 3, 1))) must beEqualTo(
      """/-------
         /|  x  |
         /|     |
         /|     |
         /|     |
         /|     |
         /-------
      """.stripMargin('/').trim
    )
  }
}
