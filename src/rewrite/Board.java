package rewrite;

import java.util.ArrayList;
import java.util.List;

public class Board<T extends Piece> {
    private List<List<T>> board;

    public Board(int rows, int cols) {
        List<List<T>> board = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            List<T> listRow = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                listRow.add(null);
            }
            board.add(listRow);
        }

        this.board = board;
    }

    public T getPiece(int row, int col) {
        return board.get(row).get(col);
    }

    public void setPiece(int row, int col, T piece) {
        board.get(row).set(col, piece);
    }

    public void removePiece(int row, int col) {
        board.get(row).set(col, null);
    }

    public boolean isEmpty(int row, int col) {
        return board.get(row).get(col) == null;
    }

    public List<List<T>> getBoard() {
        return board;
    }

    public void printReprBoard() {
        for(List<T> row : board) {
            for(T piece : row) {
                if(piece == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(piece.getType().toString().charAt(0) + " ");
                }
            }
            System.out.println();
        }
    }
}
