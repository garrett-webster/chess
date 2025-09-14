package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;

public class KingMoveCalculator extends MoveCalculator{
    KingMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        addKingMoves();
    }

    private void addKingMoves() {
        int[][] kingModifiers = {
                {1,-1},
                {1,0},
                {1,1},
                {0, 1},
                {0,-1},
                {-1,-1},
                {-1,0},
                {-1,1}
        };

        int row = this.position.getRow();
        int col = this.position.getColumn();

        for (int[] mod: kingModifiers) {
            int newRow = row+mod[0];
            int newCol = col+mod[1];

            if(newRow > 0 && newRow < 9 && newCol > 0 && newCol <9) {
                checkCollisionAndAddMove(row+mod[0], col+mod[1]);
            }
        }
    }
}
