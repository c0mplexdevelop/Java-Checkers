package rewrite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BoardController {
    private final int rows;
    private final int cols;

    private final Board<Piece> board;

    private final HBox[][] squares;

    @FXML
    private GridPane rootGrid;

    private final Player whitePlayer, blackPlayer;

    public BoardController(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.whitePlayer = new Player(PieceType.WHITE);
        this.blackPlayer = new Player(PieceType.BLACK);
        squares = new HBox[rows][cols];
        board = new Board<>(rows, cols);
        System.out.println(board.getBoard());
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
                    board.setPiece(row, col, new Piece(PieceType.BLACK));
                } else if(row > 4) {
                    board.setPiece(row, col, new Piece(PieceType.WHITE));
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
    }

    //TODO: Add piece movement
    private void move(Piece piece, int row, int col) {
        if(!board.isEmpty(row, col)) {
            return; //Not empty
        }
        int oldCol = piece.getCol();
        int oldRow = piece.getRow();

        board.setPiece(row, col, piece);
        board.deletePiece(oldRow, oldCol);
    }
    //TODO: Add piece capture
    //TODO: Add piece promotion
    //TODO: Add win condition
    //TODO: Add turn system
    //TODO OPTIONAL: Add a better image for the pieces
}
