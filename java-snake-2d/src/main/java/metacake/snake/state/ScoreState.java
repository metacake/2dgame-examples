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
    private static final Font font = new Font("Arial", Font.PLAIN, 16);

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
        DrawInstruction withScore = new PlaceInstruction(new TextInstruction("Game Over!", font, Color.BLACK), 250, 250, EMPTY_SCENE);
        DrawInstruction image = new PlaceInstruction(new TextInstruction("Your score is " + score, font, Color.BLACK), 250, 300, withScore);
        return bundle.add(DrawingDevice.NAME(), image);
    }
}