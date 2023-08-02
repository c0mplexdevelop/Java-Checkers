package old.factories;

import old.pieces.Piece;

public interface PieceFactory {
    Piece createPiece(int row, int col, boolean isKing);
}
