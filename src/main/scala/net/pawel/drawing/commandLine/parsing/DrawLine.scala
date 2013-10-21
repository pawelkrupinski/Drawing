package net.pawel.drawing.commandLine.parsing

import ValidateParameterTypes._
import net.pawel.drawing.tools.{Point, HorizontalLine, VerticalLine, Line}
import net.pawel.drawing.commands.Draw

object DrawLine extends ValidateAndParseCommand(
  commandCharacter = 'L',
  validate = ValidateLineParameters,
  parse = {
    case List(x1, y1, x2, y2) => Draw(Line(x1.toInt, y1.toInt, x2.toInt, y2.toInt))
  }
)

object ValidateLineParameters extends ValidateParameters {
  val parameterTypeValidator = new ValidateParameterTypes(repeat(4, validateInt _))

  override def apply(parameters: List[String]): Option[String] = {
    val parameterFailures = parameterTypeValidator.apply(parameters)

    if (!parameterFailures.isEmpty) {
      parameterFailures
    } else {
      val List(x0, y0, x1, y1) = parameters.map(_.toInt)
      val point = Point(x0, y0)
      val otherPoint = Point(x1, y1)
      if (!VerticalLine.sameXCoordinate(point, otherPoint) && !HorizontalLine.sameYCoordinate(point, otherPoint)) {
        Some("Line with these coordinates is neither horizontal nor vertical")
      } else {
        None
      }
    }
  }
}
