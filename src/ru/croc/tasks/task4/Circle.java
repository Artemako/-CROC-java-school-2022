package src.ru.croc.tasks.task4;

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
    public String toString() {
        return "Circle" + " (" + x + ", " + y + "), " + r + ":";
    }
}
