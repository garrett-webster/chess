package chess.MoveCalculators;

import chess.ChessBoard;
import chess.ChessPosition;

public class KingMoveCalculator extends MoveCalculator{
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

    KingMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        addMovesFromModifiers(kingModifiers);
    }
}
