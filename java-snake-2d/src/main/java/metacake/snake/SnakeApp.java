package metacake.snake;

import io.metacake.core.BootstrapBuilder;
import io.metacake.core.process.Transition;
import io.metacake.s2d.input.keyboard.KeyboardDevice;
import io.metacake.s2d.output.drawing.DrawingDevice;
import io.metacake.s2d.window.GraphicsWindow;
import metacake.snake.state.SetupState;

public class SnakeApp {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static void main(String... args) {
        BootstrapBuilder builder = new BootstrapBuilder();
        builder.withWindow(new GraphicsWindow(WIDTH, HEIGHT)).
                withInputDevices(new KeyboardDevice()).
                withOutputDevices(new DrawingDevice()).
                withInitialTransition(Transition.to(new SetupState())).
                withLoopTime(250).
                createAndLaunch();
    }
}