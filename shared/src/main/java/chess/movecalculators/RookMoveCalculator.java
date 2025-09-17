package chess.movecalculators;

import chess.ChessBoard;
import chess.ChessPosition;

public class RookMoveCalculator extends MoveCalculator{

    RookMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        this.addStraightMoves();
    }
}
