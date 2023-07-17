package factories;

import pieces.Piece;

public interface PieceFactory {
    Piece createPiece(int row, int col);
}
