package net.pawel.drawing.commands

import net.pawel.drawing.tools.{Drawing, Canvas}

trait Command

case class Draw(drawing: Drawing) extends Command with (Canvas => Canvas) {
  def apply(canvas: Canvas): Canvas = canvas.draw(drawing)
}

case class Error(message: String) extends Command

case class NewCanvas(newCanvas: Canvas) extends Command

case object QuitProgram extends Command




