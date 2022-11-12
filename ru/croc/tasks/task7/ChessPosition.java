package ru.croc.tasks.task7;

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

    public static void checkMoves(ChessPosition[] moves) throws IllegalMoveException {
        boolean isPossible = true;
        for (int i = 0; i < moves.length - 1; i++) {
            ChessPosition.checkMove(moves[i], moves[i + 1]);
        }
        if (isPossible) {
            System.out.println("OK");
        }
    }

    private static void checkMove(ChessPosition first, ChessPosition second) throws IllegalMoveException {
        if (!((Math.abs(first.x - second.x) == 1 && Math.abs(first.y - second.y) == 2) || (Math.abs(first.x - second.x) == 2 && Math.abs(first.y - second.y) == 1))) {
            throw new IllegalMoveException(first, second);
        }
    }
}
