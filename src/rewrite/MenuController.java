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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardView.fxml"));
        BoardController controller = new BoardController(Integer.parseInt(rowTextField.getText()), Integer.parseInt(columnTextField.getText()));
        loader.setController(controller);
        playButton.getScene().setRoot(loader.load());
    }
}
