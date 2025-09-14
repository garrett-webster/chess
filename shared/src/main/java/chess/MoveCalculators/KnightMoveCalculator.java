package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;

public class KnightMoveCalculator extends MoveCalculator {
    KnightMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        addKnightMoves();
    }

    private void addKnightMoves() {
        int[][] knightModifiers = {
                {2,1},
                {2, -1},
                {-2,1},
                {-2, -1},
                {1,2},
                {1,-2},
                {-1,2},
                {-1,-2}
        };

        int row = this.position.getRow();
        int col = this.position.getColumn();

        for (int[] mod: knightModifiers) {
            int newRow = row+mod[0];
            int newCol = col+mod[1];

            if(newRow > 0 && newRow < 9 && newCol > 0 && newCol <9) {
                checkCollisionAndAddMove(row+mod[0], col+mod[1]);
            }
        }
    }
}
