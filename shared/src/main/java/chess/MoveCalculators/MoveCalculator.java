package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public class MoveCalculator {
    ChessPosition position;
    ChessBoard board;
    ChessPiece piece;
    ArrayList<ChessMove> moves = new ArrayList<>();

    MoveCalculator (ChessPosition position, ChessBoard board) {
        this.position = position;
        this.board = board;
        this.piece = board.getPiece(position);
    }

    public static MoveCalculator getMoveCalculator(ChessPosition position, ChessBoard board) {
        ChessPiece piece = board.getPiece(position);
        ChessPiece.PieceType type = piece.getPieceType();

        if (type == ChessPiece.PieceType.ROOK) {
            return new RookMoveCalculator(position, board);
        } else if (type == ChessPiece.PieceType.KNIGHT) {
            return new KnightMoveCalculator(position, board);
        } else if (type == ChessPiece.PieceType.BISHOP) {
            return new BishopMoveCalculator(position, board);
        } else if (type == ChessPiece.PieceType.QUEEN) {
            return new QueenMoveCalculator(position, board);
        } else if (type == ChessPiece.PieceType.KING) {
            return new KingMoveCalculator(position, board);
        } else if (type == ChessPiece.PieceType.PAWN) {
            return new PawnMoveCalculator(position, board);
        }

        return null;
    }

    public Collection<ChessMove> returnMoves() {
        return moves;
    }

    public void addStraightMoves() {
        int row = this.position.getRow();
        int col = this.position.getColumn();

        // Positive vertical moves
        for (int i = row + 1; i < 9; i++) {
            if (checkCollisionAndAddMove(i, col)) {
                break;
            }
        }

        // Negative vertical moves
        for (int i = row - 1; i > 0; i--) {
            if (checkCollisionAndAddMove(i, col)) {
                break;
            }
        }

        // Positive horizontal moves
        for (int i = col + 1; i < 9; i++) {
            if (checkCollisionAndAddMove(row, i)) {
                break;
            }
        }

        // Negative horizontal moves
        for (int i = col - 1; i > 0; i--) {
            if (checkCollisionAndAddMove(row, i)) {
                break;
            }
        }
    }

    // Returns true if there is a collision at the passed space and adds the move, unless the collision is with a
    // piece of the same team color
    private boolean checkCollisionAndAddMove(int row, int col){
        if (isCollision(row, col)){
            if (this.board.getPiece(new ChessPosition(row, col)).getTeamColor() != this.piece.getTeamColor()) {
                moves.add(new ChessMove(this.position, new ChessPosition(row, col), null));
            }
            return true;
        }
        moves.add(new ChessMove(this.position, new ChessPosition(row, col), null));
        return false;
    }

    private boolean isCollision(int row, int col) {
        return this.board.squares[row - 1][col - 1] != null;
    }
}
