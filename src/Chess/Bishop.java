package Chess;

public class Bishop extends MoveSet {
    public static boolean isValidMove(Board board, int fromRank, int fromFile, int toRank, int toFile, boolean whitesMove) {
        if (!isInBounds(toRank, toFile)) {
            return false;
        }
        else if (Math.abs(fromRank - toRank) != Math.abs(fromFile - toFile)) {
            return false;
        }

        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;
        int rankDir = fromRank < toRank ? 1 : -1;
        int fileDir = fromFile < toFile ? 1 : -1;
        int r, f;

        for (r = fromRank + rankDir, f = fromFile + fileDir;
             r != toRank && f != toFile && board.getSpace(r, f) == 0;
             r += rankDir, f += fileDir) {}
        if (r == toRank && f == toFile && (board.getColor(r, f) == 0 || board.getColor(r, f) == opColor)) {
            return true;
        } else {
            return false;
        }
    }
}
