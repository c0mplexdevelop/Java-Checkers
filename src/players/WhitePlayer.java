package players;

import exceptions.IllegalMoveException;
import exceptions.IllegalPositionException;
import pieces.Piece;

public class WhitePlayer extends CheckersPlayer {

    private final String name;

    public WhitePlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void move(Piece piece, String direction) throws IllegalMoveException {
        int pieceRow = piece.getRow();
        int pieceCol = piece.getCol();
        boolean pieceIsKing = piece.isKing();

        int deltaRow = 0;
        int deltaCol = 0;

        switch(direction.toLowerCase()) {
            case "up-left" -> {
                deltaRow = 1;
                deltaCol = -1;
            }
            case "up-right" -> {
                deltaRow = 1;
                deltaCol = 1;
            }
            case "down-left" -> {
                if(!pieceIsKing) throw new IllegalMoveException("Piece is not promoted yet!");
                deltaRow = -1;
                deltaCol = -1;
            }

            case "down-right" -> {
                if(!pieceIsKing) throw new IllegalMoveException("Piece is not promoted yet!");
                deltaRow = -1;
                deltaCol = 1;
            }

            default -> {
                throw new IllegalMoveException("Invalid direction!");
            }
        }


        if(pieceRow + deltaRow < 0 || pieceCol + deltaCol < 0 || pieceRow + deltaRow > 7 || pieceCol + deltaCol > 7) {
            throw new IllegalMoveException("Piece cannot move out of bounds!");
        }

        piece.setRow(pieceRow + deltaRow); // Plus since if its negative, it will be subtracted anyways
        piece.setCol(pieceCol + deltaCol);
    }

    public Piece getPiece(Piece[][] board, int row, int col) {
        return board[row][col];
    }

    @Override
    public boolean capture(Piece piece, Piece capturingPiece) {
        return false;
    }
}
