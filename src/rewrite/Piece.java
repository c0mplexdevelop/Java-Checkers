package rewrite;

public class Piece {
    private boolean isKing;
    private PieceType type;

    private int row, col;

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
    public int getRow() {return this.row;}
    public int getCol() {return this.col;}
    public void setRow(int row) {this.row = row;}
    public void setCol(int col) {this.col = col;}
}
