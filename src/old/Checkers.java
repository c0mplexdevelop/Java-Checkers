package old;

import old.exceptions.IllegalPositionException;
import old.exceptions.IllegalMoveException;

import old.exceptions.IllegalPromotionException;
import old.factories.BlackPieceFactory;
import old.factories.PieceFactory;
import old.factories.WhitePieceFactory;

import old.pieces.Piece;

import old.players.CheckersPlayer;
import old.players.Player;
import old.players.BlackPlayer;
import old.players.WhitePlayer;

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
//         By creating a Factory for old.pieces, we can create a new piece without having to change the
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
            board[row][col] = pieceFactory.createPiece(row, col, false);
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

//        for(Piece[] row : board) {
//            System.out.println(Arrays.toString(row));
//        }
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

    private int[] getPlayerPiece(Scanner scanner) {
        while(true) {
            System.out.print("Enter row: ");
            if (!scanner.hasNextInt()) {
                System.out.printf("The input %s is invalid. Please enter a number.\n", scanner.next());
                continue;
            }
            int row = scanner.nextInt() - 1;
            System.out.println();


            System.out.print("Enter column: ");
            if (!scanner.hasNextInt()) {
                System.out.printf("The input %s is invalid. Please enter a number.\n", scanner.next());
                continue;
            }
            int col = scanner.nextInt();
            scanner.nextLine();


            int idxRow = board.getBoard().length - row - 1, idxCol = col - 1;
            System.out.printf("New Row: %d, New Col: %d", idxRow, idxCol);
            return new int[]{idxRow, idxCol};
        }
    }

    private String getPlayerDirection(Scanner scanner) {
        while(true) {
            System.out.print("Enter direction: ");
            String direction = scanner.nextLine();
            if(direction.equals("up-left") || direction.equals("up-right") || direction.equals("down-left") || direction.equals("down-right")) {
                return direction;
            } else {
                System.out.printf("The input %s is invalid. Please enter either 'up' or 'down'.\n", direction);
            }
        }
    }

    private void updatePiecePosition(Piece[][] board, Piece piece, int[] prevPosition, boolean capturing) throws IllegalMoveException {
        if(capturing) {
            board[piece.getRow()][piece.getCol()] = piece;
            return;
        }
        int prevRow = prevPosition[0], prevCol = prevPosition[1];
        int currRow = piece.getRow(), currCol = piece.getCol();
        Piece currPiece = board[currRow][currCol];
        if(board[currRow][currCol] != null && !currPiece.equals(piece)) {
            piece.setRow(prevRow);
            piece.setCol(prevCol);
            throw new IllegalMoveException("There is already a piece at this position. %s %d %d".formatted(currPiece, currPiece.getRow(), currPiece.getCol()));
        }
        board[prevRow][prevCol] = null;
        board[currRow][currCol] = piece;
    }

    private boolean checkIfSpaceBehindIsEmpty(Piece piece, int[] prevPosition) {
        int prevRow = prevPosition[0], prevCol = prevPosition[1];
        int currRow = piece.getRow(), currCol = piece.getCol();
        int deltaRow = (currRow - prevRow) * 2, deltaCol = (currCol - prevCol) * 2;

        System.out.println(deltaRow);

        int rowBehindCapturingPiece = prevRow + deltaRow, colBehindCapturingPiece = prevCol + deltaCol;

        System.out.println(board.getBoard()[rowBehindCapturingPiece][colBehindCapturingPiece]);

        return board.getBoard()[rowBehindCapturingPiece][colBehindCapturingPiece] == null;
    }


    public static void main(String[] args) {
        Checkers checkers = new Checkers();
        Piece[][] board = checkers.board.getBoard();
        Scanner scanner = new Scanner(System.in);

        while(checkers.winner == null) {
            for(CheckersPlayer player : checkers.players) {
                checkers.printBoard();
                System.out.printf("%s's turn:\n", player.getName());

                Piece piece = null;
                while(piece == null) {
                    int[] position = checkers.getPlayerPiece(scanner);
                    System.out.println(Arrays.toString(position));
                    int row = position[0], col = position[1];
                    try {
                        piece = player.getPiece(board, row, col);
                    } catch (IllegalPositionException posException) {
                        System.out.println(posException.getMessage());
                    }
                }

                System.out.printf("%s %d %d%n", piece, piece.getRow(), piece.getCol());
                int[] prevPosition = {piece.getRow(), piece.getCol()};
                int[] savedNewPosition = new int[2];

                boolean isCapturing = false;
                while(!isCapturing) {
                    String direction = checkers.getPlayerDirection(scanner);
                    try {
                        player.move(piece, direction);
                        savedNewPosition[0] = piece.getRow();
                        savedNewPosition[1] = piece.getCol();
                        checkers.updatePiecePosition(board, piece, prevPosition, isCapturing);
                        break;
                    } catch (IllegalMoveException moveException) {
                        /*
                        updatePiecePosition would return the piece to its previous position if the move is illegal.
                        But we save the savedNewPosition to pass for capturing.
                         */
                        if(checkers.checkIfSpaceBehindIsEmpty(piece, savedNewPosition)) isCapturing = true;

                    } catch (IllegalPromotionException promotionException) {
                        System.out.println(promotionException.getMessage());
                    }
                }

                if(isCapturing) {
                    System.out.println("Capturing");
                    Piece capturedPiece = board[savedNewPosition[0]][savedNewPosition[1]];
                    System.out.println(capturedPiece);
                    player.capture(board, piece, capturedPiece);
                    try {
                        checkers.updatePiecePosition(board, piece, savedNewPosition, isCapturing);
                    } catch (IllegalMoveException moveException) {
                        System.out.println(moveException.getMessage());
                    }
                }
            }
        }
    }
}