package src.ru.croc.tasks.task7;

public class ChessPosition {
    int x;
    int y;
    String strPosition;

    public ChessPosition(String text) throws IllegalPositionException {
        strPosition = text;
        char strX = strPosition.charAt(0);
        char strY = strPosition.charAt(1);
        int intX = (int) strX - 96;
        int intY = Integer.parseInt(String.valueOf(strY));
        if (intX >= 1 && intX <= 8 && intY >= 1 && intY <= 8 && strPosition.length() == 2) {
            this.x = intX;
            this.y = intY;
        } else
            throw new IllegalPositionException(strPosition);
    }

    @Override
    public String toString(){
        return strPosition;
    }

}
