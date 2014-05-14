package metacake.snake.state;

import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.state.TransitionState;
import io.metacake.core.process.state.VoidState;
import io.metacake.s2d.input.keyboard.KeyboardDevice;
import metacake.snake.SnakeApp;
import metacake.snake.entity.Direction;
import metacake.snake.entity.Food;
import metacake.snake.entity.Snake;

import java.util.Collections;
import java.util.Random;

import static metacake.snake.input.KeyConfiguration.*;

public class SetupState extends VoidState {
    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {
        Random rand = new Random();
        Food food =  new Food(rand.nextInt(SnakeApp.WIDTH / Food.SIZE), rand.nextInt(SnakeApp.HEIGHT / Food.SIZE));
        setTransition(TransitionState.withState(new SnakeState(Snake.initialSnake(Direction.RIGHT), food))
                                     .withTriggers(wTrigger, aTrigger, sTrigger, dTrigger)
                                     .withBucket(KeyboardDevice.KEY(), wRecognizer, aRecognizer, sRecognizer, dRecognizer)
                                     .transition());
    }

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        return RenderingInstructionBundle.EMPTY_BUNDLE;
    }
}