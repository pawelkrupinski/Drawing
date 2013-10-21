package net.pawel.drawing

import net.pawel.drawing.Pixel.Colour

case class Point(x: Int, y: Int) extends Ordered[Point] {

  def compare(that: Point): Int = if (y == that.y) x - that.x else y - that.y

  def toPixel(colour: Colour) = Pixel(this, colour)

  lazy val neighbours = List(
    Point(x, y - 1),
    Point(x, y + 1),
    Point(x - 1, y),
    Point(x + 1, y)
  )
}
