package net.pawel.drawing.tools

import net.pawel.drawing.tools.Pixel.Colour


case class Pixel(point: Point, colour: Colour) extends Ordered[Pixel] {
  def compare(that: Pixel): Int = point.compare(that.point)
  def toTuple = (point, colour)
}

object Pixel {
  type Colour = Char
}