package Chess;

public class King extends MoveSet {
    public static boolean isValidMove(Board board, int fromRank, int fromFile, int toRank, int toFile, boolean whitesMove) {
        if (!isInBounds(toRank, toFile)) {
            return false;
        }
        if (putsInCheck(board, toRank, toFile, whitesMove)) {
            return false;
        }

        return checkMove(board, fromRank, fromFile, toRank, toFile, whitesMove);
    }

    static boolean checkMove(Board board, int fromRank, int fromFile, int toRank, int toFile, boolean whitesMove) {
        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;

        boolean toSpaceIsEmpty = board.getSpace(toRank, toFile) == 0;
        boolean toSpaceIsOp = board.getColor(toRank, toFile) == opColor;
        boolean isOneSpace = (Math.abs(fromRank - toRank) == 0 || Math.abs(fromRank - toRank) == 1) &&
                             (Math.abs(fromFile - toFile) == 0 || Math.abs(fromFile - toFile) == 1);

        return isOneSpace && (toSpaceIsEmpty || toSpaceIsOp);
    }

    public static boolean putsInCheck(Board board, int rank, int file, boolean whitesMove) {
        // Iterate through board. For each op piece found, check if the destination space is a valid move for it.
        // If the space is a valid move for the op player, the king would be put in check.
        // Return true if king would be put in check, false if not
        int opColor = whitesMove ? Piece.BLACK : Piece.WHITE;
        boolean isThreat = false;

        for (int r = 1; r <= Board.RANKS; r++) {
            for (int f = 1; f <= Board.FILES; f++) {
                if (board.getColor(r, f) == opColor) {
                    int piece = board.getSpace(r, f);
                    switch (Piece.getStr(piece)) {
                        case "WP":
                        case "BP":
                            isThreat = Pawn.isValidMove(board, r, f, rank, file, !whitesMove);
                            break;
                        case "WR":
                        case "BR":
                            isThreat = Rook.isValidMove(board, r, f, rank, file, !whitesMove);
                            break;
                        case "WN":
                        case "BN":
                            isThreat = Knight.isValidMove(board, r, f, rank, file, !whitesMove);
                            break;
                        case "WB":
                        case "BB":
                            isThreat = Bishop.isValidMove(board, r, f, rank, file, !whitesMove);
                            break;
                        case "WQ":
                        case "BQ":
                            isThreat = Queen.isValidMove(board, r, f, rank, file, !whitesMove);
                            break;
                        case "WK":
                        case "BK":
                            isThreat = checkMove(board, r, f, rank, file, !whitesMove);
                            break;
                        default:
                            System.err.println("Unknown Piece????");
                            break;
                    }

                    if (isThreat) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
