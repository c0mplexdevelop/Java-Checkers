package factories;

import pieces.Piece;
import pieces.WhitePiece;
public class WhitePieceFactory implements PieceFactory {
    @Override
    public Piece createPiece(int row, int col, boolean isKing) {
        return new WhitePiece(row, col, isKing);
    }
}
