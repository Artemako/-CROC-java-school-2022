package src.ru.croc.tasks.task7;

public class ChessPositionFactory {
    public ChessPosition createChessPosition(String stringMove) throws IllegalPositionException {
        return new ChessPosition(stringMove);
    }
}
