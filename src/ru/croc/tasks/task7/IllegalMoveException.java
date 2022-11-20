package src.ru.croc.tasks.task7;

public class IllegalMoveException extends Exception{
    public String strMoves;
    public IllegalMoveException(ChessPosition first, ChessPosition second) {
        strMoves = first.strPosition + " -> " + second.strPosition;
    }

    @Override
    public String toString(){
        return "Конь так не ходит: " + strMoves;
    }
}
