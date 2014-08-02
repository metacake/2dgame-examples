import io.metacake.core.BootstrapBuilder
import io.metacake.core.process.Transition
import io.metacake.s2d.input.keyboard.KeyboardDevice
import io.metacake.s2d.input.window.{WindowActionTrigger, WindowDevice}
import io.metacake.s2d.output.drawing.DrawingDevice
import io.metacake.s2d.process.recognizers.window.WindowResizeRecognizer
import io.metacake.s2d.window.GraphicsWindow
import metacake.resize.state.ResizeState

object Main {
  val WIDTH: Int = 500
  val HEIGHT: Int = 500

  def main(args: Array[String]) {
    val builder: BootstrapBuilder = new BootstrapBuilder
    builder.withWindow(new GraphicsWindow(WIDTH, HEIGHT))
      .withInputDevices(new KeyboardDevice, new WindowDevice)
      .withOutputDevices(new DrawingDevice)
      .withLoopTime(150)
      .withInitialTransition(Transition.to(new ResizeState)
                                       .withTriggers(new WindowActionTrigger)
                                       .withBucket(WindowDevice.Key, WindowDevice.Recognizer))
      .createAndLaunch()
  }
}