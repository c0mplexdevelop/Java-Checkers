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


}
