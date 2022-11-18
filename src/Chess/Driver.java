/** Chess
 * @author Chris Macholtz
 * @version 1.0
 * */

package Chess;

import ChrisIR4.ChrisIR4;

public class Driver {
    final static String TITLE = "Chess";
    final static String BORDER = "---------------------";
    final static String SENTINEL = "q";

    final static String MAIN_MENU =
            "1. Play Chess\n" +
            "2. Quit\n";
    final static int PLAY_SELECTION = 1;
    final static int EXIT_SELECTION = 2;

    final static String MOVE_PROMPT = "Please enter a move, or 'q' to quit: ";

    final static String MOVE_FORMAT_EXAMPLE = "Move format: <rank><file>-<rank><file> (e.g. a1-b2)";
    final static String INVALID_SELECTION = "Invalid selection.";
    final static String BAD_FORMAT_ERROR = "Move is not formatted correctly.";
    final static String INVALID_FIRST_ERROR = "From move string is improperly formatted.";
    final static String INVALID_SECOND_ERROR = "To move string is improperly formatted.";
    final static String MISSING_DASH_ERROR = "Move string is missing a '-'.";

    public static void main(String[] args) {
        ChrisIR4.printTitle(BORDER, TITLE);
        playChess();

        ChrisIR4.printProgramComplete();
    }

    static void playChess() {
        // Print board
        // Get move selection
        // Check if valid. If valid, invoke move
        Board board = new Board();

        String move;
        boolean whitesMove = true;
        board.print();
        move = getMove(MOVE_PROMPT);
        while (!board.hasCheckmate()) {
            if (move.toLowerCase().equals(SENTINEL)) {
                break;
            }

            if (board.makeMove(move, whitesMove)) {
                board.print();
                // Status messages
            } else {
                board.print();
                System.out.println("Unable to move there.");
            }
            //whitesMove = !whitesMove;

            move = getMove(MOVE_PROMPT);
        }
    }

    static String getMove(String prompt) {
        String move = ChrisIR4.getString(prompt).trim();
        while (isInvalidMove(move)) {
            System.out.println(MOVE_FORMAT_EXAMPLE);
            move = ChrisIR4.getString(prompt).trim();
        }
        return move;
    }

    static boolean isInvalidMove(String move) {
        if (move.toLowerCase().equals(SENTINEL)) {
            return false;
        }
        if (move.length() != 5) {
            System.out.println(BAD_FORMAT_ERROR);
            return true;
        }
        if (move.charAt(0) < 'a' || move.charAt(0) > Board.MAX_FILE || move.charAt(1) < '1' || move.charAt(1) > Board.MAX_RANK) {
            System.out.println(INVALID_FIRST_ERROR);
            return true;
        }
        if (move.charAt(2) != '-') {
            System.out.println(MISSING_DASH_ERROR);
            return true;
        }
        if (move.charAt(3) < 'a' || move.charAt(3) > Board.MAX_FILE || move.charAt(4) < '0' || move.charAt(4) > Board.MAX_RANK) {
            System.out.println(INVALID_SECOND_ERROR);
            return true;
        }
        return false;
    }
}
