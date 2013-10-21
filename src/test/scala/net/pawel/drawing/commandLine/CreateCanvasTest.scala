package net.pawel.drawing.commandLine

import org.specs2.matcher.JUnitMustMatchers
import org.junit.Test

class CreateCanvasTest extends JUnitMustMatchers {

  @Test def wrong_number_of_parameters {
    CreateCanvas.parse(Nil) must beLeft
    CreateCanvas.parse(List("")) must beLeft
    CreateCanvas.parse(List("", "", "")) must beLeft
  }

  @Test def parameters_are_not_ints {
    CreateCanvas.parse(List("a", "1")) must beLeft
    CreateCanvas.parse(List("a", "b")) must beLeft
    CreateCanvas.parse(List("1", "a")) must beLeft
  }
}