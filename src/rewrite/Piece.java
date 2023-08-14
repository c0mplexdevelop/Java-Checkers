package rewrite;

public class Piece {
    private boolean isKing;
    private PieceType type;

    public Piece(PieceType type) {
        this.type = type;
        isKing = false;
    }

    public PieceType getType() {
        return type;
    }

    public boolean isKing() {
        return isKing;
    }

    public void promote() {
        isKing = true;
    }
}
