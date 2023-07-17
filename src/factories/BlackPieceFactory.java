package factories;

import pieces.Piece;
import pieces.BlackPiece;

public class BlackPieceFactory implements PieceFactory {
    @Override
    public Piece createPiece(int row, int col) {
        return new BlackPiece(row, col);
    }
}
