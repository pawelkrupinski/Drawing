package net.pawel.drawing

import net.pawel.drawing.Pixel.Colour

trait Drawing {
  def draw(queryCanvas: Point => Option[Colour]): Set[Pixel]
}
