package Chess;

public class Knight extends MoveSet {
    public static boolean isValidMove(Board board, int fromRank, int fromFile, int toRank, int toFile, boolean whitesMove) {
        if (!isInBounds(toRank, toFile)) {
            return false;
        }
        boolean validKnightMove = (Math.abs(fromRank - toRank) == 2 && Math.abs(fromFile - toFile) == 1) ||
                                  (Math.abs(fromRank - toRank) == 1 && Math.abs(fromFile - toFile) == 2);
        if (!validKnightMove) {
            return false;
        }

        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;
        if (board.getColor(toRank, toFile) == 0 || board.getColor(toRank, toFile) == opColor) {
            return true;
        } else {
            return false;
        }
    }
}
