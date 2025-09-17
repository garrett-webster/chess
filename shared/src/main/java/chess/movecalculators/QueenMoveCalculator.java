package chess.movecalculators;

import chess.ChessBoard;
import chess.ChessPosition;

public class QueenMoveCalculator extends MoveCalculator{
    QueenMoveCalculator(ChessPosition position, ChessBoard board) {
        super(position, board);
        addDiagMoves();
        addStraightMoves();
    }
}
