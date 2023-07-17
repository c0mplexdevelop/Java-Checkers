import factories.BlackPieceFactory;
import factories.PieceFactory;
import factories.WhitePieceFactory;
import pieces.BlackPiece;
import pieces.Piece;
import pieces.WhitePiece;
import players.BlackPlayer;
import players.WhitePlayer;

public class Checkers {
    Board<Piece> board;
    WhitePlayer player1;
    BlackPlayer player2;

    public Checkers() {
        board = new Board<>();
        player1 = new WhitePlayer("players.Player 1");
        player2 = new BlackPlayer("players.Player 2");

        fillBoard(board.getBoard());
    }

    private void fillBoard(Piece[][] board) {
        PieceFactory whiteFactory = new WhitePieceFactory();
        PieceFactory blackFactory = new BlackPieceFactory();

        for(int i = 0; i < board.length; i++) {
            if(i < 3) {
                placePieces(board, i, whiteFactory);
            } else if(i > 4) {
                placePieces(board, i, blackFactory);
            }
        }
    }

//    private void placePiecesInEvenRows(Piece[][] board, int row, PieceFactory pieceFactory) {
//        /*
//         For Future Me: This is done so that the method is somewhat adhering to SOLID principles.
//         By creating a Factory for pieces, we can create a new piece without having to change the
//         code in this method. This is because the factory will create the piece for us.
//         */
//        board[row][0] = pieceFactory.createPiece(row, 0);
//        board[row][2] = pieceFactory.createPiece(row, 2);
//        board[row][4] = pieceFactory.createPiece(row, 4);
//        board[row][6] = pieceFactory.createPiece(row, 6);
//    }
//
//    private void placePiecesInOddRows(Piece[][] board, int row, PieceFactory pieceFactory) {
//        board[row][1] = pieceFactory.createPiece(row, 1);
//        board[row][3] = pieceFactory.createPiece(row, 3);
//        board[row][5] = pieceFactory.createPiece(row, 5);
//        board[row][7] = pieceFactory.createPiece(row, 7);
//    }

    private void placePieces(Piece[][] board, int row, PieceFactory pieceFactory) {
        /*
        Better implementation of the above methods.
         */

        int startCol = row % 2 == 0 ? 0 : 1; // If row is even, start at 0, else start at 1
        for(int col = startCol; startCol < board[row].length; startCol += 2) {
            board[row][col] = pieceFactory.createPiece(row, startCol);
        }

    }
}