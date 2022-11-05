package ru.croc.tasks.task5;

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
    public boolean checkByPoint(int x, int y) {
        return (x >= xA && x <= xB && y >= yA && y <= yB);
    }

    @Override
    public void move(int dx, int dy) {
        xA += dx;
        yA += dy;
        xB += dx;
        yB += dy;
    }

    @Override
    public String toString() {
        return "Rectangle" + " (" + xA + ", " + yA + "), " + "(" + xB + ", " + yB + "):";
    }
}
