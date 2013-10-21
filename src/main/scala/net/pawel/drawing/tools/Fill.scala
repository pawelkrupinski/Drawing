package net.pawel.drawing.tools

import scala.collection.immutable.Queue
import scala.Some
import net.pawel.drawing.tools.Pixel.Colour

case class Fill(point: Point, colour: Colour) extends Drawing {

  def alreadyColouredWithFillColour(pointsColour: Colour) = pointsColour == colour

  def draw(queryCanvas: Point => Option[Colour]): Set[Pixel] = {
    val initialPointsColour = queryCanvas(point)
    val onCanvasAndNotAlreadyFilledWithTheColour =
      initialPointsColour.filterNot(alreadyColouredWithFillColour).toSet[Colour]

    for (colourToFill <- onCanvasAndNotAlreadyFilledWithTheColour;
         solutionPixel <- Fill.draw(Queue(point), colourToFill, queryCanvas).map(_.toPixel(colour))
    ) yield solutionPixel
  }

}

object Fill {
  def draw(queue: Queue[Point],
           colourToFill: Colour,
           queryCanvas: Point => Option[Colour],
           result: Set[Point] = Set.empty): Set[Point] = {
    if (queue.isEmpty) {
      result
    } else {
      def shouldBeFilled(point: Point) = queryCanvas(point) == Some(colourToFill)

      val (point, remainingQueue) = queue.dequeue

      val neighboursToVisit = point
        .neighbours
        .filterNot(remainingQueue.contains)
        .filterNot(result.contains)
        .filter(shouldBeFilled)

      val queueWithNeighboursAdded = neighboursToVisit.foldLeft(remainingQueue)(_.enqueue(_))

      draw(queueWithNeighboursAdded, colourToFill, queryCanvas, result ++ Set(point))
    }
  }
}
