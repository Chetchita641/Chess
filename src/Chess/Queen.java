package Chess;

public class Queen extends MoveSet {
    public static boolean isValidMove(Board board, int fromRank, int fromFile, int toRank, int toFile, boolean whitesMove) {
        if (!isInBounds(toRank, toFile)) {
            return false;
        }

        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;
        int dir, r, f;
        boolean toSpaceIsEmpty = board.getColor(toRank, toFile) == 0;
        boolean toSpaceIsOp = board.getColor(toRank, toFile) == opColor;

        // Vertical moves
        if (fromFile == toFile) {
            dir = fromRank < toRank ? 1 : -1;
            for (r = fromRank + dir; r != toRank && board.getSpace(r, fromFile) == 0; r += dir) {}
            if (r == toRank && (toSpaceIsEmpty || toSpaceIsOp)) {
                return true;
            }
        }
        // Horizontal moves
        if (fromRank == toRank) {
            dir = fromFile < toFile ? 1 : -1;
            for (f = fromFile + dir; f != toFile && board.getSpace(fromRank, f) == 0; f += dir) {}
            if (f == toFile && (toSpaceIsEmpty || toSpaceIsOp)) {
                return true;
            }
        }
        // Diagonal moves
        if (Math.abs(fromRank - toRank) == Math.abs(fromFile - toFile)) {
            int rankDir = fromRank < toRank ? 1 : -1;
            int fileDir = fromFile < toFile ? 1 : -1;
            for (r = fromRank + rankDir, f = fromFile + fileDir;
                 r != toRank && f != toFile && board.getSpace(r, f) == 0;
                 r += rankDir, f += fileDir) {}
            if (r == toRank && f == toFile && (toSpaceIsEmpty || toSpaceIsOp)) {
                return true;
            }
        }

        return false;
    }
}
