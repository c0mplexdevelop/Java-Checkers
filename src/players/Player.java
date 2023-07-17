package players;

public interface Player {
    String getName();
    void move(Piece piece, int row, int col);
    boolean capture(Piece piece, Piece capturingPiece);
}
