package metacake.snake.state;

import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.Transition;
import io.metacake.core.process.state.GameState;
import io.metacake.s2d.input.keyboard.KeyboardDevice;
import metacake.snake.SnakeApp;
import metacake.snake.entity.Direction;
import metacake.snake.entity.Food;
import metacake.snake.entity.Snake;

import java.util.Random;

import static metacake.snake.input.KeyConfiguration.*;

public class SetupState implements GameState {
    @Override
    public Transition tick(long delta, ActionRecognizerPipe pipe) {
        Random rand = new Random();
        Food food =  new Food(rand.nextInt(SnakeApp.WIDTH / Food.SIZE), rand.nextInt(SnakeApp.HEIGHT / Food.SIZE));
        return Transition.to(new SnakeState(Snake.initialSnake(Direction.RIGHT), food))
                .withTriggers(wTrigger, aTrigger, sTrigger, dTrigger)
                .withBucket(KeyboardDevice.KEY(), wRecognizer, aRecognizer, sRecognizer, dRecognizer);
    }
}