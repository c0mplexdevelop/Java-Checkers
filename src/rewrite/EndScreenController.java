package rewrite;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
    We in the end game now.
 */
public class EndScreenController {

    @FXML
    private Label winnerLabel;

    @FXML
    private Button exitButton;

    private final String winner;

    public EndScreenController(PieceType winner) {
        this.winner = winner == PieceType.WHITE ? "White" : "Black";
    }

    @FXML
    private void initialize() {
        winnerLabel.setText(winner + " wins!");
    }

    @FXML
    private void onExitButtonClick() {
        Platform.exit();
        System.exit(0);
    }

}
