public class Board<T> {

    private final T[][] board;

    private static final int DEFAULT_SIZE = 8;
    public Board() {
        this.board = createBoard(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public T[][] createBoard(int rows, int cols) {
        return (T[][]) new Object[rows][cols];
    }

    public T[][] getBoard() {
        return this.board;
    }



}
