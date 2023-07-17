package pieces;

public class WhitePiece extends CheckersPiece implements Piece{
    public WhitePiece(int row, int col) {
        super(row, col);
        System.out.println(this.row + " " + this.col);
    }
}
