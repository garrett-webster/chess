package chess.move_calculators;

import chess.ChessBoard;
import chess.ChessPosition;

public class BishopMoveCalculator extends MoveCalculator{

    BishopMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        addDiagMoves();
    }
}
