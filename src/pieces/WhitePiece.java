package pieces;

public class WhitePiece extends CheckersPiece implements Piece{
    public WhitePiece(int row, int col, boolean isKing) {
        super(row, col, isKing);
        System.out.println(this.row + " " + this.col);
    }
}
