package factories;

import pieces.Piece;
import pieces.WhitePiece;
public class WhitePieceFactory implements PieceFactory {
    @Override
    public Piece createPiece(int row, int col) {
        return new WhitePiece(row, col);
    }
}
