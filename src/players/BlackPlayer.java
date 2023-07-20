package players;
import exceptions.IllegalMoveException;
import exceptions.IllegalPositionException;
import pieces.CheckersPiece;
import pieces.Piece;

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
    public void move(Piece piece, String direction) throws IllegalMoveException {
        int pieceRow = piece.getRow();
        int pieceCol = piece.getCol();
        boolean pieceIsKing = piece.isKing();

        int deltaRow = 0;
        int deltaCol = 0;
        switch(direction.toLowerCase()) {
            case "down-left" -> {
                deltaRow = -1;
                deltaCol = -1;
            }
            case "down-right" -> {
                deltaRow = -1;
                deltaCol = 1;
            }

            case "up-left" -> {
                if(pieceIsKing) throw new IllegalMoveException("Piece is not promoted yet!");
                deltaRow = 1;
                deltaCol = -1;
            }

            case "up-right" -> {
                if(!pieceIsKing) throw new IllegalMoveException("Piece is not promoted yet!");
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

        piece.setRow(pieceRow + deltaRow); // Plus since if its negative, it will be subtracted anyway
        piece.setCol(pieceCol + deltaCol);
    }
    @Override
    public boolean capture(Piece piece, Piece capturingPiece) {
        return false;
    }
}
