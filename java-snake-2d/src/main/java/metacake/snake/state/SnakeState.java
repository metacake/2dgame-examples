package metacake.snake.state;

import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.state.VoidState;
import io.metacake.s2d.output.drawing.DrawingDevice;
import io.metacake.s2d.output.drawing.instructions.DrawInstruction;
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction;
import metacake.snake.entity.Snake;

import java.awt.*;

import static metacake.snake.SnakeApp.*;

public class SnakeState extends VoidState {

    private static final DrawInstruction EMPTY_SCENE = new RectangleInstruction(WIDTH, HEIGHT, Color.WHITE);
    private Snake snake;

    public SnakeState(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {}

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        RenderingInstructionBundle bundle = new RenderingInstructionBundle();
        bundle.add(DrawingDevice.NAME(), snake.draw(EMPTY_SCENE));
        return bundle;
    }
}