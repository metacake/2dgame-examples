package metacake.snake.entity;

public enum Direction {
    UP(0, -1), DOWN(0, 1), RIGHT(1, 0), LEFT(-1, 0);

    private int changeX, changeY;

    Direction(int changeX, int changeY) {
        this.changeX = changeX;
        this.changeY = changeY;
    }

    public int getChangeX() {
        return changeX;
    }

    public int getChangeY() {
        return changeY;
    }
}