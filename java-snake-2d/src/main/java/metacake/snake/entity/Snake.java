package metacake.snake.entity;

import io.metacake.s2d.output.drawing.instructions.DrawInstruction;

import java.util.LinkedList;

public class Snake implements Drawable {

    public static Snake initialSnake(Direction direction) {
        LinkedList<SnakeSegment> head = new LinkedList<>();
        head.add(new SnakeSegment(0, 0));
        return new Snake(direction, head);
    }

    private Direction direction;
    private LinkedList<SnakeSegment> segments;

    Snake(Direction direction, LinkedList<SnakeSegment> segments) {
        this.direction = direction;
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        SnakeSegment snakeHead = segments.getFirst();
        SnakeSegment head = new SnakeSegment(direction.getChangeX() + snakeHead.getX(), direction.getChangeY() + snakeHead.getY());
        segments.addFirst(head);
        segments.removeLast();
    }

    public void eat() {
        SnakeSegment snakeHead = segments.getFirst();
        SnakeSegment head = new SnakeSegment(direction.getChangeX() + snakeHead.getX(), direction.getChangeY() + snakeHead.getY());
        segments.addFirst(head);
    }

    public boolean canEat(Food food) {
        SnakeSegment snakeHead = segments.getFirst();
        return snakeHead.getX() == food.getX() && snakeHead.getY() == food.getY();
    }

    public boolean isEatingSelf() {
        SnakeSegment snakeHead = segments.getFirst();
        return segments.stream().skip(1).anyMatch((segment) -> segment.equals(snakeHead));
    }

    @Override
    public String toString() {
        return segments.toString();
    }

    public int getHeadX() {
        return segments.getFirst().getX();
    }

    public int getHeadY() {
        return segments.getFirst().getY();
    }

    public int size() {
        return segments.size();
    }
}