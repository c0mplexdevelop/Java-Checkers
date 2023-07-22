package factories;

import pieces.CheckersPiece;
import pieces.Piece;
import pieces.PieceType;

public class WhitePieceFactory implements PieceFactory {
    @Override
    public Piece createPiece(int row, int col, boolean isKing) {
        return new CheckersPiece(row, col, isKing, PieceType.WHITE);
    }
}
