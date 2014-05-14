package metacake.snake.entity;

import io.metacake.s2d.output.drawing.instructions.DrawInstruction;

public interface Drawable {
    DrawInstruction draw(DrawInstruction instruction);
}