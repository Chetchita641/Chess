package Chess;

public class Pawn extends Piece {
    public static boolean isValidMove(Board board, int fromRank, int fromFile, int rank, int file, boolean whitesMove)
    {
        int f = whitesMove ? 1 : -1;
        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;
        boolean canMoveOne = isInBounds(fromRank + f, fromFile) && board.getSpace(fromRank + f, fromFile) == 0;
        boolean canMoveTwo = whitesMove ? fromRank == 2 && board.getSpace(fromRank+f*2, fromFile) == 0
                                        : fromRank == 7 && board.getSpace(fromRank+f*2, fromFile) == 0;
        boolean canCaptureLeft = isInBounds(fromRank+f, fromFile-1)
                && board.getColor(fromRank+f, fromFile-1) == opColor;
        boolean canCaptureRight = isInBounds(fromRank+f, fromFile+1)
                && board.getColor(fromRank+f, fromFile+1) == opColor;

        if (canMoveOne && rank == fromRank+f && file == fromFile) {
            return true;
        } else if (canMoveTwo && rank == fromRank+f*2 && file == fromFile) {
            return true;
        } else if (canCaptureLeft && rank == fromRank+f && file == fromFile-1) {
            return true;
        } else if (canCaptureRight && rank == fromRank+f && file == fromFile+1) {
            return true;
        }
        return false;
    }
}
