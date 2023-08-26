package rewrite;

import javafx.fxml.FXML;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class SquareController {
    private final int row, col;

    private boolean isClicked = false;
    private boolean isHighlighted = false;

    @FXML
    private HBox squareVBox;


    public SquareController(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isClicked() {
        return this.isClicked;
    }
    public boolean isHighlighted() {return this.isHighlighted;}

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

//        System.out.println(squareVBox.getStyleClass());

    }

    public void setToHighlight() {
        if(row % 2 == 0) {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().add("highlight-white");
                squareVBox.getStyleClass().removeAll("white-square");
            } else {
                squareVBox.getStyleClass().add("highlight-black");
                squareVBox.getStyleClass().removeAll("black-square");
            }
        } else {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().add("highlight-black");
                squareVBox.getStyleClass().removeAll("black-square");
            } else {
                squareVBox.getStyleClass().add("highlight-white");
                squareVBox.getStyleClass().removeAll("white-square");
            }
        }

        isHighlighted = true;
    }

    public void removeHighlight() {
        if(row % 2 == 0) {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().removeAll("highlight-white");
                squareVBox.getStyleClass().add("white-square");
            } else {
                squareVBox.getStyleClass().removeAll("highlight-black");
                squareVBox.getStyleClass().add("black-square");
            }
        } else {
            if(col % 2 == 0) {
                squareVBox.getStyleClass().removeAll("highlight-black");
                squareVBox.getStyleClass().add("black-square");
            } else {
                squareVBox.getStyleClass().removeAll("highlight-white");
                squareVBox.getStyleClass().add("white-square");
            }
        }

        isHighlighted = false;
    }

    public void setPiece() {
        squareVBox.getStyleClass().add("red-piece");
    }

    public void removePiece() {
        squareVBox.getStyleClass().remove("red-piece");
    }

    public void setWhitePiece() {
        if(squareVBox.getStyleClass().contains("red-piece")) return;
        squareVBox.getStyleClass().add("red-piece"); //FIXME: Add white and black piece
    }

    public void setBlackPiece() {
        if(squareVBox.getStyleClass().contains("red-piece")) return;
        squareVBox.getStyleClass().add("red-piece"); //FIXME: Add white and black piece
    }
}
