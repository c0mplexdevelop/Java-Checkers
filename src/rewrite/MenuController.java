package rewrite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Rectangle2D;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.stage.Screen;


public class MenuController {
    @FXML
    private TextField rowTextField, columnTextField;

    @FXML
    private Button playButton;

    private final Stage primaryStage;

    public MenuController(Stage stage) {
        this.primaryStage = stage;
    }
    @FXML
    private void initialize() {
        Rectangle2D screenResolution = Screen.getPrimary().getVisualBounds();
        if(screenResolution.getWidth() < 900 || screenResolution.getHeight() < 900) {
            primaryStage.setWidth(640);
            primaryStage.setHeight(640);
        }

        playButton.setOnAction(e -> {
            System.out.println("Play button clicked");
            try {
                onPlayButtonClick();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void onPlayButtonClick() throws Exception {
        int rows = rowTextField.getText().isEmpty() ? 8 : Integer.parseInt(rowTextField.getText());
        int cols = columnTextField.getText().isEmpty() ? 8 : Integer.parseInt(columnTextField.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardView.fxml"));
        BoardController controller = new BoardController(rows, cols);
        loader.setController(controller);
        playButton.getScene().setRoot(loader.load());
    }
}
