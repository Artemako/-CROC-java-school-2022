package src.ru.croc.tasks.task4;

public class Rectangle extends Figure {
    public int xA;
    public int yA;
    public int xB;
    public int yB;
    public Rectangle(int xA, int yA, int xB, int yB) {
        this.xA = xA;
        this.yA = yA;
        this.xB = xB;
        this.yB = yB;
    }

    @Override
    public String toString() {
        return "Rectangle" + " (" + xA + ", " + yA + "), " + "(" + xB + ", " + yB + "):";
    }
}
