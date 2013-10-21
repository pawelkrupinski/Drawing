package net.pawel.drawing

import net.pawel.drawing.Pixel.Colour

case class Rectangle(corner: Point, anotherCorner: Point) extends Drawing {

  lazy val lines = Set(
    Line(Point(corner.x, corner.y), Point(corner.x, anotherCorner.y)),
    Line(Point(corner.x, corner.y), Point(anotherCorner.x, corner.y)),
    Line(Point(anotherCorner.x, anotherCorner.y), Point(anotherCorner.x, corner.y)),
    Line(Point(anotherCorner.x, anotherCorner.y), Point(corner.x, anotherCorner.y))
  )

  def draw(queryCanvas: Point => Option[Colour]): Set[Pixel] =
    lines.flatMap(_.draw(queryCanvas))
}

object Rectangle {
  def apply(x1: Int, y1: Int, x2: Int, y2: Int): Rectangle =
    Rectangle(Point(x1, y1), Point(x2, y2))
}