package net.pawel.drawing

object PrintCanvas {
  def apply(canvas: Canvas) = {
    val upperAndLowerBorder = (1 to canvas.width + 2).map(_ => '-').mkString("")

    def toColour(point: Point) = canvas.query(point).get

    val points = canvas.points.toList

    val printedLines =
      for (y <- 1 to canvas.height)
        yield points
            .filter(_.y == y)
            .map(toColour)
            .mkString("|", "", "|")

    printedLines.mkString(upperAndLowerBorder + "\n", "\n", "\n" + upperAndLowerBorder)
  }
}
