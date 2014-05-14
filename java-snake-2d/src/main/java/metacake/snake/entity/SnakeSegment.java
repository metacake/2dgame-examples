package metacake.snake.entity;

import io.metacake.s2d.output.drawing.instructions.DrawInstruction;
import io.metacake.s2d.output.drawing.instructions.PlaceInstruction;
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction;

import java.awt.*;

class SnakeSegment extends Positionable implements Drawable {

    private static final Color COLOR = Color.GREEN;
    private static final int SIZE = 50;
    private static final DrawInstruction IMAGE = new RectangleInstruction(SIZE, SIZE, COLOR);

    SnakeSegment(int x, int y) {
        super(x, y);
    }

    @Override
    public DrawInstruction draw(DrawInstruction instruction) {
        return new PlaceInstruction(IMAGE, getX() * SIZE, getY() * SIZE, instruction);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SnakeSegment) {
            SnakeSegment segment = (SnakeSegment) obj;
            return segment.getX() == this.getX() && segment.getY() == this.getY();
        } else {
             return false;
        }
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}