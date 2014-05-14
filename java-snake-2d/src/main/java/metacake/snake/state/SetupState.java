package metacake.snake.state;

import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.state.TransitionState;
import io.metacake.core.process.state.VoidState;
import io.metacake.s2d.input.keyboard.KeyboardDevice;
import metacake.snake.entity.Direction;
import metacake.snake.entity.Snake;

import java.util.Collections;

import static metacake.snake.input.KeyConfiguration.*;

public class SetupState extends VoidState {
    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {
        setTransition(TransitionState.withState(new SnakeState(Snake.initialSnake(Direction.RIGHT)))
                                     .withTriggers(wTrigger, aTrigger, sTrigger, dTrigger)
                                     .withBucket(KeyboardDevice.KEY(), wRecognizer, aRecognizer, sRecognizer, dRecognizer)
                                     .transition());
    }

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        return RenderingInstructionBundle.EMPTY_BUNDLE;
    }
}