package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root,900,900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.show();
        Controller controller = new Controller(root,scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}