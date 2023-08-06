package rewrite;

import javafx.fxml.FXML;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SquareController {
    int row, col;

    @FXML
    private HBox squareVBox;

    public SquareController(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @FXML
    private void initialize() {
        if(row % 2 == 0) {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().add("white-square");
            } else {
                squareVBox.getStyleClass().add("black-square");
            }
        } else {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().add("black-square");
            } else {
                squareVBox.getStyleClass().add("white-square");
            }
        }

        System.out.println(squareVBox.getStyleClass());

    }
}
