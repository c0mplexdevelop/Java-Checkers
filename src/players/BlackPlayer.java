package players;
import pieces.Piece;

public class BlackPlayer implements Player {
    private final String name;

    public BlackPlayer(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void move(Piece piece, int row, int col) {

    }

    public Piece getPiece(Piece[][] board, int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean capture(Piece piece, Piece capturingPiece) {
        return false;
    }
}
