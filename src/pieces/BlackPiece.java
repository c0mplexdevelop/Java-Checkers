package pieces;

public class BlackPiece extends CheckersPiece{
    public BlackPiece(int row, int col, boolean isKing) {
        super(row, col, isKing);
        System.out.println(this.row + " " + this.col);
    }
}
