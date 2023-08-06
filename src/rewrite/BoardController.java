package rewrite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;



public class BoardController {
    private final int rows;
    private final int cols;

    @FXML
    private GridPane rootGrid;

    public BoardController(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @FXML
    private void initialize() throws Exception {
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / rows);

            boardGridPane.getRowConstraints().add(row);
        }

        for(int j = 0; j < cols; j++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / cols);
            boardGridPane.getColumnConstraints().add(col);
        }

        //TODO: Add different colored squares
        //TODO: Add pieces to the board
        //TODO: Add piece movement
        //TODO: Add piece capture
        //TODO: Add piece promotion
        //TODO: Add win condition
        //TODO: Add turn system
        //TODO OPTIONAL: Add a better image for the pieces
    }
}
