package metacake.snake.state;

import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.state.EndState;
import io.metacake.core.process.state.VoidState;
import io.metacake.s2d.output.drawing.DrawingDevice;
import io.metacake.s2d.output.drawing.instructions.DrawInstruction;
import io.metacake.s2d.output.drawing.instructions.PlaceInstruction;
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction;
import io.metacake.s2d.output.drawing.instructions.TextInstruction;
import metacake.snake.entity.Snake;

import java.awt.*;

import static metacake.snake.SnakeApp.HEIGHT;
import static metacake.snake.SnakeApp.WIDTH;

public class ScoreState extends VoidState {

    private static final DrawInstruction EMPTY_SCENE = new RectangleInstruction(WIDTH, HEIGHT, Color.WHITE);

    private int score;
    public ScoreState(Snake snake) {
        this.score = snake.size();
    }

    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {
        setTransition(EndState.endWith(this));
    }

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        RenderingInstructionBundle bundle = new RenderingInstructionBundle();
        DrawInstruction image = new PlaceInstruction(new TextInstruction("Game Over!", Font.getFont("Arial"), Color.BLACK), 50, 50, EMPTY_SCENE);
        return bundle.add(DrawingDevice.NAME(), image);
    }
}