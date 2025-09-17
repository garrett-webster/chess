package chess.movecalculators;

import chess.*;

public class PawnMoveCalculator extends MoveCalculator {

ChessPiece.PieceType[] promotableTypes = {
        ChessPiece.PieceType.BISHOP,
        ChessPiece.PieceType.QUEEN,
        ChessPiece.PieceType.KNIGHT,
        ChessPiece.PieceType.ROOK};

    PawnMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        addMoves();
    }

    private void addMoves() {
        int row = position.getRow();
        int col = position.getColumn();

        int direction = directionModifier(piece.getTeamColor());
        boolean canMoveTwice = (piece.getTeamColor() == ChessGame.TeamColor.WHITE && row == 2) ||
                (piece.getTeamColor() == ChessGame.TeamColor.BLACK && row == 7);

        // Move forward one space
        if (!isCollision(row+direction, col)){
            addPawnMove(row+direction, col);
        }

        // Move forward two spaces
        if(canMoveTwice && !isCollision(row+direction, col) && !isCollision(row+direction*2, col)) {
            addPawnMove(row+direction*2, col);
        }

        // Captures
        pawnCaptureCheck(row+direction, col+1);
        pawnCaptureCheck(row+direction, col-1);

    }

    private int directionModifier(ChessGame.TeamColor color) {
        if(color == ChessGame.TeamColor.WHITE) {
            return 1;
        } else {
            return -1;
        }
    }

    private void addPawnMove(int row, int col){
        if (row == 8 || row == 1) {
            for (ChessPiece.PieceType type : promotableTypes) {
                moves.add(new ChessMove(this.position, new ChessPosition(row, col), type));
            }
        } else {
            moves.add(new ChessMove(this.position, new ChessPosition(row, col), null));
        }
    }

    private void pawnCaptureCheck(int row, int col) {
        if (isInRange(row, col) && isCollision(row, col)){
            if (this.board.getPiece(new ChessPosition(row, col)).getTeamColor() != this.piece.getTeamColor()) {
                addPawnMove(row, col);
            }
        }
    }
}
