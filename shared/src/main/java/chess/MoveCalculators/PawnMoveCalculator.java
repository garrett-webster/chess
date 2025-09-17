package chess.MoveCalculators;

import chess.*;

public class PawnMoveCalculator extends MoveCalculator {

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

        // TODO: Add border checks against going off the board
        // Move forward one space
        checkCollisionAndAddMove(row+direction, col);

        // Move forward two spaces
        if(canMoveTwice) {
            checkCollisionAndAddMove(row+direction*2, col);
        }

        // Captures
        pawnCaptureCheck(row+1, col+1);
        pawnCaptureCheck(row+1, col-1);

    }

    private int directionModifier(ChessGame.TeamColor color) {
        if(color == ChessGame.TeamColor.WHITE) {
            return 1;
        } else {
            return -1;
        }
    }

    private void pawnCaptureCheck(int row, int col) {
        if (isCollision(row, col)){
            if (this.board.getPiece(new ChessPosition(row, col)).getTeamColor() != this.piece.getTeamColor()) {
                moves.add(new ChessMove(this.position, new ChessPosition(row, col), null));
            }
        }
    }
}
