package net.pawel.drawing

import net.pawel.drawing.Pixel.Colour

object Line {
  val Colour = 'x'

  def apply(startPoint: Point, endPoint: Point): Drawing =
    if (VerticalLine.sameXCoordinate(startPoint, endPoint)) {
      VerticalLine(startPoint, endPoint)
    } else {
      HorizontalLine(startPoint, endPoint)
    }

  def apply(x1: Int, y1: Int, x2: Int, y2: Int): Drawing = apply(Point(x1, y1), Point(x2, y2))

  def toPixel(point: Point) = point.toPixel(Colour)
}

case class VerticalLine(startPoint: Point, length: Int) extends Drawing {
  val startY = startPoint.y
  val x = startPoint.x

  def draw(queryCanvas: Point => Option[Colour]): Set[Pixel] =
    (startY to (startY + length))
      .map(toPoint)
      .map(Line.toPixel)
      .toSet

  def toPoint(y: Int) = Point(x, y)
}

object VerticalLine {
  def apply(point: Point, anotherPoint: Point): VerticalLine = {
      val List(firstPoint, secondPoint) = List(point, anotherPoint).sorted
      VerticalLine(firstPoint, secondPoint.y - firstPoint.y)
  }

  def sameXCoordinate(point: Point, anotherPoint: Point) = point.x == anotherPoint.x
}

case class HorizontalLine(startPoint: Point, length: Int) extends Drawing {
  val startX = startPoint.x
  val y = startPoint.y

  def draw(queryCanvas: Point => Option[Colour]): Set[Pixel] =
    (startX to (startX + length))
      .map(toPoint)
      .map(Line.toPixel)
      .toSet

  def toPoint(x: Int) = Point(x, y)
}

object HorizontalLine {
  def apply(point: Point, anotherPoint: Point): HorizontalLine = {
      val List(firstPoint, secondPoint) = List(point, anotherPoint).sorted
      HorizontalLine(firstPoint, secondPoint.x - firstPoint.x)
  }

  def sameYCoordinate(point: Point, anotherPoint: Point) = point.y == anotherPoint.y
}

