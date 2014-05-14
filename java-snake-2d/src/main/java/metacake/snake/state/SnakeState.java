package metacake.snake.state;

import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.ActionRecognizerPipe;
import io.metacake.core.process.state.VoidState;

public class SnakeState extends VoidState {
    @Override
    public void update(long delta, ActionRecognizerPipe recognizers) {

    }

    @Override
    public RenderingInstructionBundle renderingInstructions() {
        return RenderingInstructionBundle.EMPTY_BUNDLE;
    }
}