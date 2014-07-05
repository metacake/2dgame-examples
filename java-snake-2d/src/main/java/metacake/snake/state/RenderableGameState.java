package metacake.snake.state;

import io.metacake.core.output.RenderingInstructionBundle;
import io.metacake.core.process.state.GameState;

public interface RenderableGameState extends GameState {

    public RenderingInstructionBundle render();
}