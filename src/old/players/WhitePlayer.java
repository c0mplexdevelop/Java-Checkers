package old.players;

import old.exceptions.IllegalMoveException;
import old.exceptions.IllegalPromotionException;
import old.pieces.Piece;

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
    public void move(Piece piece, String direction) throws IllegalMoveException, IllegalPromotionException {
        int pieceRow = piece.getRow();
        int pieceCol = piece.getCol();
        boolean pieceIsKing = piece.isKing();

        int deltaRow = 0;
        int deltaCol = 0;

        switch(direction.toLowerCase()) {
            case "up-left" -> {
                deltaRow = -1;
                deltaCol = -1;
            }
            case "up-right" -> {
                deltaRow = -1;
                deltaCol = 1;
            }
            case "down-left" -> {
                if(!pieceIsKing) throw new IllegalPromotionException("Piece is not promoted yet!");
                deltaRow = 1;
                deltaCol = -1;
            }

            case "down-right" -> {
                if(!pieceIsKing) throw new IllegalPromotionException("Piece is not promoted yet!");
                deltaRow = 1;
                deltaCol = 1;
            }

            default -> {
                throw new IllegalMoveException("Invalid direction!");
            }
        }


        if(pieceRow + deltaRow < 0 || pieceCol + deltaCol < 0 || pieceRow + deltaRow > 7 || pieceCol + deltaCol > 7) {
            throw new IllegalMoveException("Piece cannot move out of bounds!");
        }
        int newRow = pieceRow + deltaRow;
        int newCol = pieceCol + deltaCol;
        System.out.printf("New row: %d, new col: %d%n", newRow, newCol);
        piece.setRow(newRow); // Plus since if its negative, it will be subtracted anyways
        piece.setCol(newCol);
    }

    public boolean capture(Piece[][] board, Piece piece, Piece capturedPiece) {
        int pieceRow = piece.getRow(), pieceCol = piece.getCol();
        int capturedRow = capturedPiece.getRow(), capturedCol = capturedPiece.getCol();
        int deltaRow = (capturedRow - pieceRow) * 2, deltaCol = (capturedCol - pieceCol) * 2;

//        if (deltaRow < 0 || deltaCol < 0 || deltaRow > 7 || deltaCol > 7) {
//            System.out.println("Piece cannot move out of bounds!");
//            return false;
//        }

        if(board[pieceRow + deltaRow][pieceCol + deltaCol] != null) {
            System.out.println("There is already a piece at this position!");
            return false;
        }

        piece.setRow(pieceRow + deltaRow);
        piece.setCol(pieceCol + deltaCol);
        System.out.printf("Captured piece at row: %d, col: %d%n", capturedRow, capturedCol);

        board[capturedRow][capturedCol] = null;
        return true;
    }
}
