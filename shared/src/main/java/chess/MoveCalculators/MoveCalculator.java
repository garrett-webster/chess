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

        int[][] modifiers = {
                {1,0},
                {-1,0},
                {0,1},
                {0,-1}
        };

        for (int[] mod: modifiers) {
            for(int i = 1; i < 8; i++){
                int newRow = row+i*mod[0];
                int newCol = col+i*mod[1];

                if(newRow > 0 && newRow < 9 && newCol > 0 && newCol <9) {
                    if (checkCollisionAndAddMove(newRow, newCol)) {
                        break;
                    }
                }
            }
        }
    }

    public void addDiagMoves() {
        int row = this.position.getRow();
        int col = this.position.getColumn();
        int[][] diag_modifiers = {
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1}
        };

        for (int[] mod: diag_modifiers){
            for(int i = 1; row+i*mod[0] < 9 && row+i*mod[0] >0 && col+i*mod[1] < 9  && col+i*mod[1] > 0; i++) {
                if (checkCollisionAndAddMove(row+i*mod[0],col+i*mod[1])) {
                    break;
                }
            }
        }
    }

    // Returns true if there is a collision at the passed space and adds the move, unless the collision is with a
    // piece of the same team color
    boolean checkCollisionAndAddMove(int row, int col){
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
