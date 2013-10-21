package net.pawel.drawing.endToEnd

import org.junit.Test
import net.pawel.drawing.endToEnd.tools.Scenario

class EndToEndTest {

  @Test
  def sample_io = Scenario("sample_io.txt").run()

  @Test
  def lines = Scenario("lines.txt").run()

  @Test
  def create_canvas = Scenario("create_canvas.txt").run()

  @Test
  def rectangles = Scenario("rectangles.txt").run()

  @Test
  def fill = Scenario("fill.txt").run()



}
