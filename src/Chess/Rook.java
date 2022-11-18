package Chess;

public class Rook extends MoveSet {
    public static boolean isValidMove(Board board, int fromRank, int fromFile, int toRank, int toFile, boolean whitesMove) {
        if (!isInBounds(toRank, toFile)) {
            return false;
        }

        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;
        int dir, r, f;

        // Vertical move
        if (fromFile == toFile) {
            dir = fromRank < toRank ? 1 : -1;
            for (r = fromRank + dir; r != toRank && board.getSpace(r, fromFile) == 0; r += dir) {}
            if (r == toRank && (board.getColor(r, fromFile) == 0 || board.getColor(r, fromFile) == opColor)) {
                return true;
            }
        }
        // Horizontal move
        else if (fromRank == toRank) {
            dir = fromFile < toFile ? 1 : -1;
            for (f = fromFile + dir; f != toFile && board.getSpace(fromRank, f) == 0; f += dir) {}
            if (f == toFile && (board.getColor(fromRank, f) == 0 || board.getColor(fromRank, f) == opColor)) {
                return true;
            }
        }
        return false;
    }
}
