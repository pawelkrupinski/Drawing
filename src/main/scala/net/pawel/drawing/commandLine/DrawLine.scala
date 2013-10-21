package net.pawel.drawing.commandLine

import net.pawel.drawing.{HorizontalLine, Point, VerticalLine, Line}
import ParameterTypeValidator._

object DrawLine extends CommandParser {
  val commandCharacter: Char = 'L'

  val validator = LineParametersValidator

  def create(parameters: List[String]) = parameters.map(_.toInt) match {
    case List(x1, y1, x2, y2) => canvas => canvas.map(_.draw(Line(x1, y1, x2, y2)))
  }
}

case object LineParametersValidator extends ParametersValidator {
  val parameterTypeValidator = new ParameterTypeValidator(repeat(4, validateInt _))

  override def apply(parameters: List[String]): List[Failure] = {
    val parameterFailures = parameterTypeValidator.apply(parameters)

    if (!parameterFailures.isEmpty) {
      parameterFailures
    } else {
      val List(x0, y0, x1, y1) = parameters.map(_.toInt)
      val point = Point(x0, y0)
      val otherPoint = Point(x1, y1)
      if (!VerticalLine.sameXCoordinate(point, otherPoint) && !HorizontalLine.sameYCoordinate(point, otherPoint)) {
        List(Failure("Line with these coordinates is neither horizontal nor vertical"))
      } else {
        Nil
      }
    }
  }
}
