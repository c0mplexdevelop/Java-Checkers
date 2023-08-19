package rewrite;

import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class SquareController {
    private final int row, col;

    private boolean isClicked = false;

    @FXML
    private HBox squareVBox;


    public SquareController(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isClicked() {
        return this.isClicked;
    }

    public void setToClicked() {
        this.isClicked = true;
    }

    public void setNotClicked() {
        this.isClicked = false;
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

    public void setToHighlight() {
        if(row % 2 == 0) {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().add("highlight-white");
            } else {
                squareVBox.getStyleClass().add("highlight-black");
            }
        } else {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().add("highlight-black");
            } else {
                squareVBox.getStyleClass().add("highlight-white");
            }
        }
    }

    public void removeHighlight() {
        if(row % 2 == 0) {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().remove("highlight-white");
            } else {
                squareVBox.getStyleClass().remove("highlight-black");
            }
        } else {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().remove("highlight-black");
            } else {
                squareVBox.getStyleClass().remove("highlight-white");
            } else {
                squareVBox.getStyleClass().remove("highlight-black");
            }
        }

    public void setPiece() {
        squareVBox.getStyleClass().add("red-piece");
    }

    public void removePiece() {
        squareVBox.getStyleClass().remove("red-piece");
    }
}
