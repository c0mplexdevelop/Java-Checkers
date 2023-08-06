package rewrite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuController {
    @FXML
    private TextField rowTextField, columnTextField;

    @FXML
    private Button playButton;

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
