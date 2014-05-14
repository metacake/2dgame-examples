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
import metacake.snake.SnakeApp;
import metacake.snake.entity.Direction;
import metacake.snake.entity.Food;
import metacake.snake.entity.Snake;

import java.awt.*;
import java.util.Random;

import static metacake.snake.SnakeApp.*;
import static metacake.snake.input.KeyConfiguration.*;

public class SnakeState extends VoidState {

    private static final DrawInstruction EMPTY_SCENE = new RectangleInstruction(WIDTH, HEIGHT, Color.WHITE);
    private Snake snake;
    private Food food;

    public SnakeState(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {
        setSnakeDirection(recognizers.emptyBucket(KeyboardDevice.KEY()));
        if (snake.canEat(food)) {
            snake.eat();
            newFood();
        } else {
            snake.move();
        }

    }

    void newFood() {
        Random rand = new Random();
        this.food = new Food(rand.nextInt(SnakeApp.WIDTH / Food.SIZE), rand.nextInt(SnakeApp.HEIGHT / Food.SIZE));
    }

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        RenderingInstructionBundle bundle = new RenderingInstructionBundle();
        bundle.add(DrawingDevice.NAME(), food.draw(snake.draw(EMPTY_SCENE)));
        return bundle;
    }

    void setSnakeDirection(CustomizableMap<ActionRecognizerName, KeyActionRecognizer> recognizers) {
        if (recognizers.get(W).triggerWeight() > 0) snake.setDirection(Direction.UP);
        if (recognizers.get(A).triggerWeight() > 0) snake.setDirection(Direction.LEFT);
        if (recognizers.get(S).triggerWeight() > 0) snake.setDirection(Direction.DOWN);
        if (recognizers.get(D).triggerWeight() > 0) snake.setDirection(Direction.RIGHT);
    }
}