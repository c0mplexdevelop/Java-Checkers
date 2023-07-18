package players;
import pieces.Piece;

public interface Player {
    String getName();
    void move(Piece piece, String direction);

    Piece getPiece(Piece[][] board, int row, int col);
    boolean capture(Piece piece, Piece capturingPiece);
}
