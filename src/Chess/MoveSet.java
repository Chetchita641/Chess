package Chess;

public class MoveSet {
    public static boolean isInBounds(int rank, int file) {
        return (rank >= 1 && rank <= Board.MAX_RANK && file >= 1 && file <= Board.MAX_FILE);
    }
}
