package metacake.snake.entity;

import io.metacake.s2d.output.drawing.instructions.DrawInstruction;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Snake implements Drawable {

    public static Snake initialSnake() {
        Collection<SnakeSegment> head = new LinkedList<>();
        head.add(new SnakeSegment(0, 0));
        return new Snake(head);
    }

    private Collection<SnakeSegment> segments;

    Snake(Collection<SnakeSegment> segments) {
        this.segments = segments;
    }

    @Override
    public DrawInstruction draw(DrawInstruction instruction) {
        DrawInstruction image = instruction;
        for (SnakeSegment segment : segments) {
            image = segment.draw(image);
        }
        return image;
    }
}