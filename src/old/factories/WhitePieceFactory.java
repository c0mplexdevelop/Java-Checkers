package old.factories;

import old.pieces.CheckersPiece;
import old.pieces.Piece;
import old.pieces.PieceType;

public class WhitePieceFactory implements PieceFactory {
    @Override
    public Piece createPiece(int row, int col, boolean isKing) {
        return new CheckersPiece(row, col, isKing, PieceType.WHITE);
    }
}
