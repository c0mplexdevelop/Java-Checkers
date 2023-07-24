import exceptions.IllegalPositionException;
import exceptions.IllegalMoveException;

import factories.BlackPieceFactory;
import factories.PieceFactory;
import factories.WhitePieceFactory;

import pieces.Piece;

import players.CheckersPlayer;
import players.Player;
import players.BlackPlayer;
import players.WhitePlayer;

import java.util.Arrays;
import java.util.Scanner;

public class Checkers {
    private final Board<Piece> board;

    private final CheckersPlayer[] players = new CheckersPlayer[2];

    private Player winner;

    public Checkers() {
        board = new Board<>(Piece.class);
        for (int i = 0; i < players.length; i++) {
            players[i] = i == 0 ? new WhitePlayer("White") : new BlackPlayer("Black");
        }

        fillBoard(board.getBoard());
    }

    private void fillBoard(Piece[][] board) {
        PieceFactory whiteFactory = new WhitePieceFactory();
        PieceFactory blackFactory = new BlackPieceFactory();

        for(int i = 0; i < board.length; i++) {
            if(i < 3) {
                placePieces(board, i, blackFactory);
            } else if(i > 4) {
                placePieces(board, i, whiteFactory);
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
        int boardLength = board[row].length;

        for(int col = startCol; col < boardLength; col += 2) {
            board[row][col] = pieceFactory.createPiece(row, startCol, false);
        }
    }

    private void printBoard() {
        Piece[][] board = this.board.getBoard();
        int rowLength = board.length;
        int colLength = board[0].length;
        for (int rowIdx = 0; rowIdx < rowLength; rowIdx++) {
            System.out.printf("%d |", (8-rowIdx));
            for (int colIdx = 0; colIdx < colLength; colIdx++) {
                Piece piece = board[rowIdx][colIdx];
                printPiece(piece);
                printBoardEdge(colIdx, colLength);
            }
        }
        showColumns(colLength);

        for(Piece[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void showColumns(int colLength) {
        String firstIndent = "     ";
        System.out.print(firstIndent);
        for(int i = 0; i < colLength; i++) {
            System.out.printf("%d     ", i + 1);
        }
        System.out.println();
    }

    public void printPiece(Piece piece) {
        if(piece == null) {
            System.out.print("     ");
        } else {
            System.out.printf(" %s ", piece);
        }
    }

    public void printBoardEdge(int col_idx, int length) {
        if(col_idx != length - 1) {
            System.out.print("|");
        } else {
            System.out.println();
        }
    }

    private int[] getPlayerInput(Scanner scanner) {
        while(true) {
            System.out.print("Enter row: ");
            int row = scanner.nextInt();
            if (!scanner.hasNextInt()) {
                System.out.printf("The input %s is invalid. Please enter a number.\n", scanner.nextLine());
                continue;
            }

            System.out.print("Enter column: ");
            int col = scanner.nextInt();
            if (!scanner.hasNextInt()) {
                System.out.printf("The input %s is invalid. Please enter a number.\n", scanner.nextLine());
                continue;
            }

            int idxRow = row - 1, idxCol = col - 1;
            return new int[]{idxRow, idxCol};
        }
    }


    public static void main(String[] args) {
        Checkers checkers = new Checkers();
        Piece[][] board = checkers.board.getBoard();
        Scanner scanner = new Scanner(System.in);

        while(checkers.winner == null) {
            for(Player player : checkers.players) {
                checkers.printBoard();
                System.out.printf("%s's turn:\n", player.getName());

                Piece piece = null;
                while(piece == null) {
                    int[] position = checkers.getPlayerInput(scanner);
                    int row = position[0], col = position[1];
                    try {
                        piece = player.getPiece(board, row, col);
                    } catch (IllegalPositionException posException) {
                        System.out.println(posException.getMessage());
                        continue;
                    }
                }


            }
        }

    }
}