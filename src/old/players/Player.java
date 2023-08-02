package old.players;
import old.exceptions.IllegalMoveException;
import old.exceptions.IllegalPositionException;
import old.exceptions.IllegalPromotionException;
import old.pieces.Piece;

public interface Player {
    String getName();
    void move(Piece piece, String direction) throws IllegalMoveException, IllegalPromotionException;

    Piece getPiece(Piece[][] board, int row, int col) throws IllegalPositionException;

    boolean capture(Piece[][] board, Piece piece, Piece capturedPiece);
}
