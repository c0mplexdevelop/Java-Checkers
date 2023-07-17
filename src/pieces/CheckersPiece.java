package pieces;

public abstract class CheckersPiece implements Piece {
    protected int row;
    protected int col;

    public CheckersPiece(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
