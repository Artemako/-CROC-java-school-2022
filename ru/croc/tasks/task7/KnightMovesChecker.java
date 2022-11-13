package ru.croc.tasks.task7;

public class KnightMovesChecker {
    public static void checkMoves(ChessPosition[] moves) throws IllegalMoveException {
        for (int i = 0; i < moves.length - 1; i++) {
            KnightMovesChecker.checkMove(moves[i], moves[i + 1]);
        }
        System.out.println("OK");
    }

    private static void checkMove(ChessPosition first, ChessPosition second) throws IllegalMoveException {
        if (!((Math.abs(first.x - second.x) == 1 && Math.abs(first.y - second.y) == 2) ||
                (Math.abs(first.x - second.x) == 2 && Math.abs(first.y - second.y) == 1))) {
            throw new IllegalMoveException(first, second);
        }
    }
}
