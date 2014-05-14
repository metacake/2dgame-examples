package metacake.snake.entity;

import io.metacake.s2d.output.drawing.instructions.DrawInstruction;
import io.metacake.s2d.output.drawing.instructions.PlaceInstruction;
import io.metacake.s2d.output.drawing.instructions.RectangleInstruction;

import java.awt.*;

public class Food extends Positionable implements Drawable {

    private static final Color COLOR = Color.RED;
    public static final int SIZE = 50;
    private static final DrawInstruction IMAGE = new RectangleInstruction(SIZE, SIZE, COLOR);

    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public DrawInstruction draw(DrawInstruction instruction) {
        return new PlaceInstruction(IMAGE, getX() * SIZE, getY() * SIZE, instruction);
    }
}
