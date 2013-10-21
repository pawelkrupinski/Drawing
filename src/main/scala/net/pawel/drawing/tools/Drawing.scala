package net.pawel.drawing.tools

import net.pawel.drawing.tools.Pixel.Colour

trait Drawing {
  def draw(queryCanvas: Point => Option[Colour]): Set[Pixel]
}
