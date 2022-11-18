package Chess;

public class Board {
    final static int RANKS = 8;
    final static int FILES = 8;

    public final static char MAX_RANK = '1' + RANKS - 1;
    public final static char MAX_FILE = 'a' + FILES - 1;


    final static char[] COLORS = {'W', 'B'};
    final static char[] BACK_LINE = {' ', 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};

    final static String HEADER = "     a    b    c    d    e    f    g    h\n";
    final static String FOOTER = HEADER;
    final static String BORDER = "   +----+----+----+----+----+----+----+----+\n";


    final static String BLANK_SPACE_ERROR = "There is no piece in this space.";
    final static String NOT_PLAYERS_PIECE_ERROR = "This piece cannot be moved by this player.";

    static int[][] board;

    public Board() {
        board = new int[RANKS+1][FILES+1];

        //initializeBoard();
        testInitializeBoard();
    }

    public boolean makeMove(String move, boolean whitesMove) {
        // Check if piece is in the from slot
        // Check if piece can move in that direction
        // If all true, return true. Otherwise, false

        int fromRank = convertRank(move.charAt(1));
        int fromFile = convertFile(move.charAt(0));
        int fromSpace = board[fromRank][fromFile];

        int toRank = convertRank(move.charAt(4));
        int toFile = convertFile(move.charAt(3));

        // If fromSpace is blank
        if (fromSpace == 0) {
            System.out.println(BLANK_SPACE_ERROR);
            return false;
        }

        // If trying to move another player's piece
        if (whitesMove && (fromSpace < Piece.getInt("WP") || fromSpace > Piece.getInt("WK"))) {
            System.out.println(NOT_PLAYERS_PIECE_ERROR);
            return false;
        }
        else if (!whitesMove && (fromSpace < Piece.getInt("BP") || fromSpace > Piece.getInt("BK"))) {
            System.out.println(NOT_PLAYERS_PIECE_ERROR);
            return false;
        }

        boolean isValidMove = false;
        switch (Piece.getStr(fromSpace)) {
            case "WP":
            case "BP":
                isValidMove = Pawn.isValidMove(this, fromRank, fromFile, toRank, toFile, whitesMove);
                break;
            case "WR":
            case "BR":
                isValidMove = Rook.isValidMove(this, fromRank, fromFile, toRank, toFile, whitesMove);
                break;
            case "WN":
            case "BN":
                isValidMove = Knight.isValidMove(this, fromRank, fromFile, toRank, toFile, whitesMove);
                break;
            case "WB":
            case "BB":
                isValidMove = Bishop.isValidMove(this, fromRank, fromFile, toRank, toFile, whitesMove);
                break;
            case "WQ":
            case "BQ":
                isValidMove = Queen.isValidMove(this, fromRank, fromFile, toRank, toFile, whitesMove);
                break;
            case "WK":
            case "BK":
                isValidMove = King.isValidMove(this, fromRank, fromFile, toRank, toFile, whitesMove);
                break;
            default:
                System.out.println("Unknown piece on the chessboard???");
                break;
        }

        if (isValidMove) {
            board[fromRank][fromFile] = 0;
            board[toRank][toFile] = fromSpace;
            return true;
        } else {
            return false;
        }
    }

    public int getSpace(int rank, int file) {
        if (rank >= 1 && rank <= RANKS && file >= 1 && file <= FILES) {
            return board[rank][file];
        }
        return -1;
    }

    public int getColor(int rank, int file) {
        return (board[rank][file] / 10) * 10;
    }

    public boolean hasCheckmate() {
        return false;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(HEADER);

        for (int r = RANKS; r >= 1; r--) {
            out.append(BORDER);

            out.append(r);
            out.append("  |");
            for (int f = 1; f <= FILES; f++) {
                out.append(' ');
                if (board[r][f] != 0) {
                    out.append(Piece.getStr(board[r][f]));
                    out.append(' ');
                } else {
                    out.append("   ");
                }
                out.append('|');
            }

            out.append('\n');
        }

        out.append(BORDER);
        out.append(FOOTER);
        return out.toString();
    }

    public void print() {
        System.out.println(toString());
    }

    void initializeBoard() {
        int i;
        for (i = 1; i <= 8; i++) {
            board[1][i] = Piece.getInt("W" + BACK_LINE[i]);
        }
        for (i = 1; i <= 8; i++) {
            board[2][i] = Piece.getInt("WP");
        }
        for (i = 1; i <= 8; i++) {
            board[7][i] = Piece.getInt("BP");
        }
        for (i = 1; i <= 8; i++) {
            board[8][i] = Piece.getInt("B" + BACK_LINE[i]);
        }
    }

    void testInitializeBoard() {
        board[2][5] = Piece.getInt("WQ");
    }

    int convertRank(char rank) {
        return rank - '0';
    }

    int convertFile(char file) {
        if (file >= 'A' && file <= 'H') {
            return file - 'A' + 1;
        } else if (file >= 'a' && file <= 'h') {
            return file - 'a' + 1;
        } else {
            return 0;
        }
    }
}
