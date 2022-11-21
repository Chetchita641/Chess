package Chess;

public class GameModel {
    private static GameModel instance;
    Board currentBoard;

    private boolean whitesTurn;

    private GameModel() {
        currentBoard = new Board();
        whitesTurn = true;
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void printBoard() {
        currentBoard.print();
    }

    public boolean isValidMove(char piece, String move) {
        String[] moveList;
        char rank = getRank(move);
        char file = getFile(move);
        char color = whitesTurn ? 'w' : 'b';
        switch (piece) {
            case 'p':
                moveList = Pawn.getPossMoves(currentBoard, rank, file, color);
                break;
            case 'R':
                moveList = Rook.getPossMoves(currentBoard, rank, file, color);
                break;
            case 'N':
                moveList = Knight.getPossMoves(currentBoard, rank, file, color);
                break;
            case 'B':
                moveList = Bishop.getPossMoves(currentBoard, rank, file, color);
                break;
            case 'Q':
                moveList = Queen.getPossMoves(currentBoard, rank, file, color);
                break;
            case 'K':
                moveList = King.getPossMoves(currentBoard, rank, file, color);
                break;
            default:
                System.out.println("Dude, wtf?");
                System.exit(500);
        }

        for (String m : moveList) {
            if (move.equals(m)) {
                return true;
            }
        }
        return false;
    }

    private char getRank(String move) {
        for (int i = 0; i < move.length(); i++) {
            char c = move.charAt(i);
            if (c >= 'a' && c <= 'h') {
                return c;
            }
        }
        System.out.println("Error in finding the rank!");
        return 0;
    }

    private char getFile(String move) {
        for (int i = 0; i < move.length(); i++) {
            char c = move.charAt(i);
            if (c >= '1' && c <= '8') {
                return c;
            }
        }
        System.out.println("Error in finding file!");
        return 0;
    }

    public boolean makeMove(char pieceChosen, char rank, char file, char extra, 
        boolean isCapture, boolean isCheck, boolean isCheckmate) {
        return false;
    }

    public boolean castleKingside() {
        return false;
    }

    public boolean castleQueenside() {
        return false;
    }

    public boolean hasCheckmate() {
        return false;
    }
   
    /*
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
    */
}
