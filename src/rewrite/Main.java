package rewrite;

import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXMLLoader;


public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
        MenuController controller = new MenuController(primaryStage);
        loader.setController(controller);
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Checkers");
        primaryStage.show();
    }

}
