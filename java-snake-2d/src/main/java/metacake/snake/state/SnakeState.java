package metacake.snake.state;

import io.metacake.core.common.CustomizableMap;
import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.ActionRecognizerName;
import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.state.VoidState;
import io.metacake.s2d.input.keyboard.KeyboardDevice;
import io.metacake.s2d.output.drawing.DrawingDevice;
import io.metacake.s2d.output.drawing.instructions.DrawInstruction;
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction;
import io.metacake.s2d.process.recognizers.keyboard.KeyActionRecognizer;
import metacake.snake.entity.Direction;
import metacake.snake.entity.Snake;

import java.awt.*;

import static metacake.snake.SnakeApp.*;
import static metacake.snake.input.KeyConfiguration.*;

public class SnakeState extends VoidState {

    private static final DrawInstruction EMPTY_SCENE = new RectangleInstruction(WIDTH, HEIGHT, Color.WHITE);
    private Snake snake;

    public SnakeState(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {
        setSnakeDirection(recognizers.emptyBucket(KeyboardDevice.KEY()));
        snake.move();
    }

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        RenderingInstructionBundle bundle = new RenderingInstructionBundle();
        bundle.add(DrawingDevice.NAME(), snake.draw(EMPTY_SCENE));
        return bundle;
    }

    public void setSnakeDirection(CustomizableMap<ActionRecognizerName, KeyActionRecognizer> recognizers) {
        if (recognizers.get(W).triggerWeight() > 0) snake.setDirection(Direction.UP);
        if (recognizers.get(A).triggerWeight() > 0) snake.setDirection(Direction.LEFT);
        if (recognizers.get(S).triggerWeight() > 0) snake.setDirection(Direction.DOWN);
        if (recognizers.get(D).triggerWeight() > 0) snake.setDirection(Direction.RIGHT);
    }
}