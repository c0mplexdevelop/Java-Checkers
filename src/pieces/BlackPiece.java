package pieces;

public class BlackPiece extends CheckersPiece implements Piece{
    public BlackPiece(int row, int col) {
        super(row, col);
        System.out.println(this.row + " " + this.col);
    }
}
