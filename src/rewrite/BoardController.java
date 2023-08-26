package rewrite;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.VPos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class BoardController {
    private final int rows;
    private final int cols;

    private final Board<Piece> board;

    private final HBox[][] squares;
    private final SquareController[][] squareControllers;
    private final SquareController[][] highlightedSquareControllers;

    private PieceType currentTurn = PieceType.WHITE;

    @FXML
    private GridPane rootGrid;

    private HBox clickedSquare;

    private int whitePieceCount;
    private int blackPieceCount;

    public BoardController(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        squares = new HBox[rows][cols];
        squareControllers = new SquareController[rows][cols];
        highlightedSquareControllers = new SquareController[rows][cols];
        board = new Board<>(rows, cols);
    }

    @FXML
    private void initialize() throws Exception {
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / rows);
            row.setVgrow(Priority.SOMETIMES);
            row.setValignment(VPos.CENTER);
            rootGrid.getRowConstraints().add(row);
        }

        for(int j = 0; j < cols; j++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / cols);
            col.setFillWidth(true);
            col.setHgrow(Priority.SOMETIMES);
            rootGrid.getColumnConstraints().add(col);
        }

        //DONE: Add different colored squares
        /*
        There was a bug in this code, due to SquareView.fxml Vbox uses USE_PREF_SIZE for min and max sizes. This prevents
        the proper size (computed) in the GridPane. The solution is to set the min and max sizes to USE_COMPUTED_SIZE.
         */
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SquareView.fxml"));
                SquareController controller = new SquareController(row, col);
                squareControllers[row][col] = controller;
                loader.setController(controller);
                HBox squareVBox = loader.load();
                squares[row][col] = squareVBox; // Save this so we can add it the piece to its children.
                rootGrid.add(squareVBox, col, row);
            }
        }

        //DONE: Add pieces to the board
        for(int row = 0; row < rows; row++) {
            int startCol = row % 2 == 0 ? 1 : 0;
            for(int col = startCol; col < cols; col+=2) {
                if(row < 3) {
                    board.setPiece(row, col, new Piece(row, col, PieceType.BLACK));
                    blackPieceCount++;
                } else if(row > 4) {
                    board.setPiece(row, col, new Piece(row, col, PieceType.WHITE));
                    whitePieceCount++;
                }
            }
        }

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(board.isEmpty(row, col)) { //FIXED: If there is no image in the square, it scales wrong/sizes wrong.
                    continue;
                }
                SquareController controller = squareControllers[row][col];
                controller.setPiece();
            }
        }

        for(HBox[] square : squares) {
            for(HBox squareVBox : square) {
                squareVBox.setOnMouseClicked(hboxOnClick());
            }
        }

        board.printReprBoard();
    }

    //DONE: Add piece movement
    private void move(Piece piece, int row, int col) {
        if(!board.isEmpty(row, col)) {
            return; //Not empty
        }
        int oldCol = piece.getCol();
        int oldRow = piece.getRow();

        board.setPiece(row, col, piece);
        piece.setRow(row);
        piece.setCol(col);
        board.removePiece(oldRow, oldCol);
    }

    private EventHandler<MouseEvent> hboxOnClick() {
        return event -> {
            HBox square = (HBox) event.getSource();
            System.out.println(square.getStyleClass());
            int row = GridPane.getRowIndex(square), col = GridPane.getColumnIndex(square);
            SquareController clickedController = squareControllers[row][col];
            Piece piece = board.getPiece(row, col);
            if(piece == null && !clickedController.isHighlighted()) {
                return;
            } else if(piece != null && piece.getType() != currentTurn && clickedSquare == null) {
                // clickedSquare is null only if we clicked a square with a piece on it.
                return;
            }

            if(clickedSquare != null && square != clickedSquare) {
                int oldRow = GridPane.getRowIndex(clickedSquare);
                int oldCol = GridPane.getColumnIndex(clickedSquare);
//                System.out.println(clickedController.isHighlighted());

                if(clickedController.isHighlighted()) {
                    Piece movingPiece = board.getPiece(oldRow, oldCol);
                    if(piece != null) {
                        if(!checkIfCapturable(movingPiece, piece)) return;
                        capture(movingPiece, piece);
                        if(checkIfTypeWon(currentTurn)) {
                            System.out.println(currentTurn + " won!");
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
                            EndScreenController controller = new EndScreenController(currentTurn);
                            loader.setController(controller);
                            try {
                                Stage endStage = loader.load();
                                endStage.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };

                    } else {
                        move(movingPiece, row, col);
                        updateVisualBoard();
                        clearHighlightedSquares();
                    }

                    if(checkIfPromotable(movingPiece)) {
                        promotePiece(movingPiece);
                    }
                    switchTurns(); // Moved here since if we click something and change squares, it will switch turns.
                }
                SquareController oldController = squareControllers[oldRow][oldCol];
                oldController.removeHighlight();
                clearHighlightedSquares();


                System.out.printf("Changed Clicked square: %s%n", square);
                board.printReprBoard();
                clickedSquare = null; // reset to null since we are done with the move.
                return;

            } else if(square.equals(clickedSquare)) {
                clickedSquare = null; // refer above
                SquareController controller = squareControllers[row][col];
                controller.removeHighlight();
                clearHighlightedSquares();
                System.out.printf("Removed Clicked square: %s%n", square);

                return;
            }

            clearHighlightedSquares();

            clickedSquare = square;
            highlightSquare(row, col);
            System.out.printf("Clicked square: %s%n", square);
            updateVisualBoard();
            highlightAvailableSquares(row, col);

//            if(piece.getType() == PieceType.WHITE) {
//                move(piece, row + 1, col);
//            } else {
//                move(piece, row - 1, col);
//            }
//            square.getChildren().clear();
//            square.getChildren().add(new ImageView(new Image("rewrite/white_piece.png")));
        };
    }

    private void highlightAvailableSquares(int squareRow, int squareCol) {
        Piece piece = board.getPiece(squareRow, squareCol);
        if(piece == null) {
            return;
        }
        if(piece.isKing()) { //highlight also the back part
            //DONE: Add promoted piece movement
            highlightSquare(squareRow + 1, squareCol - 1);
            highlightSquare(squareRow + 1, squareCol + 1);
            highlightSquare(squareRow - 1, squareCol - 1);
            highlightSquare(squareRow - 1, squareCol + 1);
            return;
        }
        if(piece.getType() == PieceType.BLACK) {
            highlightSquare(squareRow + 1, squareCol - 1);
            highlightSquare(squareRow + 1, squareCol + 1);
        } else {
            highlightSquare(squareRow - 1, squareCol - 1);
            highlightSquare(squareRow - 1, squareCol + 1);
        }
    }

    private void clearHighlightedSquares() {
        for(SquareController[] squareRow : highlightedSquareControllers) {
            for(SquareController square : squareRow) {
                if(square != null) {
                    square.removeHighlight();
                }
            }
        }
    }

    private void highlightSquare(int row, int col) {
        if(row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        SquareController controller = squareControllers[row][col];
        highlightedSquareControllers[row][col] = controller;
        controller.setToHighlight();
    }

    private void updateVisualBoard() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                Piece piece = board.getPiece(row, col);
                SquareController controller = squareControllers[row][col];
                if(piece == null) {
                    controller.removePiece();
                } else if(piece.getType() == PieceType.WHITE) {
                    controller.setWhitePiece();
                } else {
                    controller.setBlackPiece();
                }
            }
        }
    }

    private void printReprControllers() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                System.out.println(squareControllers[row][col]);
            }
            System.out.println();
        }
    }

    //DONE: Add piece capture
    private boolean checkIfCapturable(Piece attackingPiece, Piece capturedPiece) {
        int deltaRow = (capturedPiece.getRow() - attackingPiece.getRow());
        int deltaCol = (capturedPiece.getCol() - attackingPiece.getCol());

        int spaceBehindCapturedPieceRow = capturedPiece.getRow() + deltaRow;
        int spaceBehindCapturedPieceCol = capturedPiece.getCol() + deltaCol;

        if(spaceBehindCapturedPieceRow < 0 || spaceBehindCapturedPieceRow >= rows ||
                spaceBehindCapturedPieceCol < 0 || spaceBehindCapturedPieceCol >= cols) {
            return false;
        } else if (attackingPiece.getType() == capturedPiece.getType()){
            return false;
        }else return board.isEmpty(spaceBehindCapturedPieceRow, spaceBehindCapturedPieceCol);
    }

    private void capture(Piece attackingPiece, Piece capturedPiece) {
        int deltaRow = (capturedPiece.getRow() - attackingPiece.getRow());
        int deltaCol = (capturedPiece.getCol() - attackingPiece.getCol());

        int spaceBehindCapturedPieceRow = capturedPiece.getRow() + deltaRow;
        int spaceBehindCapturedPieceCol = capturedPiece.getCol() + deltaCol;

        if(capturedPiece.getType() == PieceType.BLACK) {
            blackPieceCount--;
        } else {
            whitePieceCount--;
        }

        board.removePiece(capturedPiece.getRow(), capturedPiece.getCol());
        board.removePiece(attackingPiece.getRow(), attackingPiece.getCol());
        board.setPiece(spaceBehindCapturedPieceRow, spaceBehindCapturedPieceCol, attackingPiece);
        SquareController capturedPieceController = squareControllers[capturedPiece.getRow()][capturedPiece.getCol()];
        SquareController attackingPieceController = squareControllers[attackingPiece.getRow()][attackingPiece.getCol()];
        SquareController spaceBehindCapturedPieceController = squareControllers[spaceBehindCapturedPieceRow][spaceBehindCapturedPieceCol];

        attackingPieceController.removePiece();
        capturedPieceController.removePiece();
        spaceBehindCapturedPieceController.setPiece();

        attackingPiece.setRow(spaceBehindCapturedPieceRow);
        attackingPiece.setCol(spaceBehindCapturedPieceCol);
    }

    //DONE: Add piece promotion
    private boolean checkIfPromotable(Piece piece) {
        if(piece.getType() == PieceType.WHITE) {
            return piece.getRow() == 0;
        } else {
            return piece.getRow() == rows - 1;
        }
    }

    private void promotePiece(Piece piece) {
        piece.promote();
    }

    //DONE: Add win condition
    private boolean checkIfTypeWon(PieceType type) {
        if(type == PieceType.WHITE) {
            return blackPieceCount == 0;
        } else {
            return whitePieceCount == 0;
        }
    }



    //DONE: Add turn system
    private void switchTurns() {
        this.currentTurn = currentTurn == PieceType.WHITE ? PieceType.BLACK : PieceType.WHITE;
    }
    //TODO OPTIONAL: Add a better image for the pieces
}
