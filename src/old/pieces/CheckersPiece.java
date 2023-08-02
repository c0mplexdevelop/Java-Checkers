package old.pieces;

public class CheckersPiece implements Piece {
    private int row;
    private int col;
    private boolean isKing;

    private PieceType type;

    private String color;

    public CheckersPiece(int row, int col, boolean isKing, PieceType type) {
        this.row = row;
        this.col = col;
        this.isKing = isKing;
        this.type = type;
        this.color = type == PieceType.WHITE ? "W" : "B";
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

    public PieceType getType() {
        return this.type;
    }

    public void promote() {
        this.isKing = true;
    }
    @Override
    public String toString() {
        return isKing ? "K%sP".formatted(color) : "%sPP".formatted(color);
    }
}
