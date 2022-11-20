package src.ru.croc.tasks.task5;

public class Circle extends Figure {
    public int x;
    public int y;
    public int r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public boolean checkByPoint(int x, int y) {
        return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) <= r * r;
    }

    @Override
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String toString() {
        return "Circle" + " (" + x + ", " + y + "), " + r + ":";
    }
}
