package ru.croc.tasks.task5;

public class Figure implements Moveable{
    public boolean checkByPoint(int x, int y) {
        return false;
    }

    @Override
    public void move(int dx, int dy) {
        System.out.println("Can't move empty figure.");
    }

    @Override
    public String toString() {
        return "Figure:";
    }
}
