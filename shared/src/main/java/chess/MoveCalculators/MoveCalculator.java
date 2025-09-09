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
        return new ArrayList<>();
    }

    public void addStraightMoves(Collection<ChessMove> moves) {
        int row = this.position.getRow();
        int col = this.position.getColumn();

        for (int i = row; i < 8; i++) {
            if (isCollision(i, col)){
                if (this.board.getPiece(new ChessPosition(i, col)).getTeamColor() == this.piece.getTeamColor()) {
//                    RETURN THIS MOVE
                } else {
//                    DON'T RETURN THIS MOVE
                }
               break;
            }
        }
    }

    private boolean isCollision(int row, int col) {
        return this.board.squares[row - 1][col - 1] != null;
    }
}
