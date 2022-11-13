package ru.croc.tasks.task7;

import java.util.List;
import java.util.Scanner;

public class Task7 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // ввод типа (a1, b2, c3), то есть без кавычек, но через запятую с пробелом
        String inputMoves = in.nextLine();
        try {
            String[] stringMoves = inputMoves.split(", ");
            ChessPosition[] moves = new ChessPosition[stringMoves.length];
            ChessPositionFactory myChessPositionFactory = new ChessPositionFactory();

            for (int i = 0; i < stringMoves.length; i++) {
                moves[i] = myChessPositionFactory.createChessPosition(stringMoves[i]);
            }

            KnightMovesChecker.checkMoves(moves);
            System.out.println(moves[0]);


        } catch (IllegalPositionException | IllegalMoveException e) {
            System.out.println(e);
        }


    }
}
