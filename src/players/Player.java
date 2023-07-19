package players;
import exceptions.IllegalMoveException;
import pieces.Piece;

public interface Player {
    String getName();
    void move(Piece piece, String direction) throws IllegalMoveException;

    Piece getPiece(Piece[][] board, int row, int col);
    boolean capture(Piece piece, Piece capturingPiece);
}
