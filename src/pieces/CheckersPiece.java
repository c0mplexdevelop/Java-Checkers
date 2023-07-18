package pieces;

public abstract class CheckersPiece implements Piece {
    protected int row;
    protected int col;
    protected boolean isKing;

    public CheckersPiece(int row, int col, boolean isKing) {
        this.row = row;
        this.col = col;
        this.isKing = isKing;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isKing() {
        return this.isKing;
    }
}
