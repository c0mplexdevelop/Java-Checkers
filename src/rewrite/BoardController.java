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

    private final HBox[][] squares;

    @FXML
    private GridPane rootGrid;

    public BoardController(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        squares = new HBox[rows][cols];
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

        //TODO: Add different colored squares
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

        //TODO: Add pieces to the board

        //TODO: Add piece movement
        //TODO: Add piece capture
        //TODO: Add piece promotion
        //TODO: Add win condition
        //TODO: Add turn system
        //TODO OPTIONAL: Add a better image for the pieces
    }
}
