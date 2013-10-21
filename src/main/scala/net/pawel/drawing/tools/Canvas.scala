package net.pawel.drawing.tools

import scala.collection.immutable.{TreeSet, TreeMap}
import scala.collection.SortedMap
import scala.Some
import net.pawel.drawing.tools.Pixel.Colour

case class Canvas(width: Int,
                  height: Int,
                  emptyColour: Colour = ' ',
                  private val drawings: Vector[Drawing] = Vector.empty[Drawing],
                  private val pixels: SortedMap[Point, Colour] = TreeMap.empty) {

  def query(point: Point): Option[Colour] =
    pixels.get(point)
      .orElse(Some(emptyColour))
      .filterNot(_ => isOutOfCanvas(point))

  def isOutOfCanvas(point: Point): Boolean = {
    point.x < 1 || point.y < 1 || point.x > width || point.y > height
  }

  def query(x: Int, y: Int): Option[Colour] = query(Point(x, y))

  def draw(drawing: Drawing) = copy(
    drawings = drawings ++ Vector(drawing),
    pixels = pixels ++ drawing.draw(query).map(_.toTuple)
  )

  lazy val points = TreeSet.empty[Point] ++
    (for (x <- 1 to width;
          y <- 1 to height) yield Point(x, y))
}

