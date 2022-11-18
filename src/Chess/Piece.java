package Chess;

public class Piece {
    public final static int WHITE = 10;
    public final static int WP = 11;
    public final static int WB = 12;
    public final static int WN = 13;
    public final static int WR = 14;
    public final static int WQ = 15;
    public final static int WK = 16;

    public final static int BLACK = 20;
    public final static int BP = 21;
    public final static int BB = 22;
    public final static int BN = 23;
    public final static int BR = 24;
    public final static int BQ = 25;
    public final static int BK = 26;

    public final static int[] PIECE_INT = {WP, WB, WN, WR, WQ, WK, BP, BB, BN, BR, BQ, BK};
    public final static String[] PIECE = {"WP", "WB", "WN", "WR", "WQ", "WK", "BP", "BB", "BN", "BR", "BQ", "BK"};

    public static int getInt(String piece) {
        piece = piece.toUpperCase();
        for (int i = 0; i < PIECE.length; i++) {
            if (PIECE[i].equals(piece)) {
                return PIECE_INT[i];
            }
        }
        return 0;
    }

    public static String getStr(int piece) {
        for (int i = 0; i < PIECE_INT.length; i++) {
            if (PIECE_INT[i] == piece) {
                return PIECE[i];
            }
        }
        return "";
    }
}
