package players;

import pieces.Piece;

public class WhitePlayer implements Player {

    private final String name;

    public WhitePlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void move(Piece piece, int row, int col) {

    }

    @Override
    public boolean capture(Piece piece, Piece capturingPiece) {
        return false;
    }
}
