package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    public ChessPiece[][] squares;

    public ChessBoard() {
        this.squares = new ChessPiece[8][8];
        this.resetBoard();
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow();
        int col = position.getColumn();

        this.squares[row - 1][col - 1] = piece;
    }

    public ChessPiece removePiece(ChessPosition position) {
        ChessPiece toRemove = this.squares[position.getRow() - 1][position.getColumn() - 1];
        this.squares[position.getRow() - 1][position.getColumn() - 1] = null;
        return toRemove;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow();
        int col = position.getColumn();

        return this.squares[row - 1][col - 1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        ChessPiece[][] defaultBoard = new ChessPiece[8][8];

        ChessPiece.PieceType[] backrank = {
                ChessPiece.PieceType.ROOK,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.KING,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.ROOK
        };

        for (int i = 0; i < 8; i++) {
            defaultBoard[0][i] = new ChessPiece(ChessGame.TeamColor.WHITE, backrank[i]);
            defaultBoard[1][i] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

            defaultBoard[6][i] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            defaultBoard[7][i] = new ChessPiece(ChessGame.TeamColor.BLACK, backrank[i]);
        }

        this.squares = defaultBoard;
    }

    public ArrayList<ChessPosition> getPiecePositions(ChessGame.TeamColor teamColor) {
        ArrayList<ChessPosition> oppositeTeamPiecePositions = new ArrayList<>();

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; i++){
                ChessPiece piece = squares[i][j];
                if (piece != null && piece.getTeamColor() != teamColor) {
                    oppositeTeamPiecePositions.add(new ChessPosition(i + 1, j + 1));
                }
            }
        }

        return oppositeTeamPiecePositions;
    }

    public ChessPosition getKingPosition(ChessGame.TeamColor teamColor) {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; i++){
                ChessPiece piece = squares[i][j];
                if (piece != null && piece.getTeamColor() != teamColor && piece.type == ChessPiece.PieceType.KING) {
                   return new ChessPosition(i + 1, j + 1);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }
}
