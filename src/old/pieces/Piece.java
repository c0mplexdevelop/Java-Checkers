package old.pieces;

public interface Piece {
    int getRow();
    int getCol();
    void setRow(int row);
    void setCol(int col);
    boolean isKing();
}
