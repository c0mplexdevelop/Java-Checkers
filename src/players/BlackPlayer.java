package players;
import exceptions.IllegalMoveException;
import pieces.CheckersPiece;
import pieces.Piece;

public class BlackPlayer implements Player {
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

        if(!pieceIsKing) {
            switch(direction.toLowerCase()) {
                case "down-left" -> {
                    deltaRow = -1;
                    deltaCol = -1;
                }
                case "down-right" -> {
                    deltaRow = -1;
                    deltaCol = 1;
                }

                case "up-left", "up-right" -> {
                    throw new IllegalMoveException("Piece is not promoted yet!");
                }

                default -> {
                    throw new IllegalMoveException("Invalid direction!");
                }
            }
        } else {
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
                    deltaRow = 1;
                    deltaCol = -1;
                }
                case "up-right" -> {
                    deltaRow = 1;
                    deltaCol = 1;
                }
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
