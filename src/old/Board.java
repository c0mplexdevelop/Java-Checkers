package old;

import java.lang.reflect.Array;

public class Board<T> {

    private T[][] board;

    private static final int DEFAULT_SIZE = 8;
    public Board(Class<T> clazz) {
        this.board = (T[][]) Array.newInstance(clazz, DEFAULT_SIZE, DEFAULT_SIZE);
    }


    public T[][] getBoard() {
        return this.board;
    }



}
