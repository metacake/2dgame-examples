package metacake.scala.snake

import io.metacake.core.BootstrapBuilder
import io.metacake.s2d.window.GraphicsWindow
import io.metacake.s2d.output.drawing.DrawingDevice
import io.metacake.s2d.input.keyboard.KeyboardDevice
import metacake.scala.snake.state.SetupState

object Snake {
  val WIDTH: Int = 500
  val HEIGHT: Int = 500

  def main(args: Array[String]) {
    val builder: BootstrapBuilder = new BootstrapBuilder
    builder.withWindow(new GraphicsWindow(WIDTH, HEIGHT))
           .withInputDevices(new KeyboardDevice)
           .withOutputDevices(new DrawingDevice)
           .withInitialState(new SetupState)
           .createAndLaunch()
  }
}