package old.players;
import old.exceptions.IllegalMoveException;
import old.exceptions.IllegalPromotionException;
import old.pieces.Piece;

public class BlackPlayer extends CheckersPlayer {
    private final String name;

    public BlackPlayer(String name) {
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
            case "down-left" -> {
                deltaRow = 1;
                deltaCol = -1;
            }
            case "down-right" -> {
                deltaRow = 1;
                deltaCol = 1;
            }

            case "up-left" -> {
                if(pieceIsKing) throw new IllegalMoveException("Piece is not promoted yet!");
                deltaRow = -1;
                deltaCol = -1;
            }

            case "up-right" -> {
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

        int newRow = pieceRow + deltaRow;
        int newCol = pieceCol + deltaCol;
        System.out.printf("New row: %d, new col: %d%n", newRow, newCol);
        piece.setRow(newRow); // Plus since if its negative, it will be subtracted anyways
        piece.setCol(newCol);
    }

    @Override
    public boolean capture(Piece[][] board, Piece piece,  Piece capturedPiece) {
        return false;
    }

}
