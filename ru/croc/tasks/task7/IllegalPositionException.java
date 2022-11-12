package ru.croc.tasks.task7;

public class IllegalPositionException extends Throwable {
    public String strPosition;
    public IllegalPositionException(String strPosition) {
        this.strPosition = strPosition;
    }

    @Override
    public String toString(){
        return "Неправильный ввод хода: " + strPosition;
    }

}
