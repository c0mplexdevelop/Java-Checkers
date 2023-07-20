package players;
import exceptions.IllegalMoveException;
import pieces.Piece;

import exceptions.IllegalPositionException;

public abstract class CheckersPlayer implements Player {
    public abstract void move(Piece piece, String direction) throws IllegalMoveException;

    public Piece getPiece(Piece[][] board, int row, int col) throws IllegalPositionException{
        int minRow = 0, minCol = 0;
        int maxRow = board.length, maxCol = board[0].length;

        if(row > maxRow || col > maxCol) {
            throw new IllegalPositionException("Position is out of bounds! Max row: %d, max col: %d".formatted(maxRow, maxCol));
        } else if(row < minRow || col < minCol) {
            throw new IllegalPositionException("Position is out of bounds! Min row: %d, min col: %d".formatted(minRow, minCol));
        }
        return board[row][col];
    }
}
