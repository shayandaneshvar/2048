package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root,900,900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.show();
        InputStream inputStream = new FileInputStream("prepare4.wav");
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
        Controller controller = new Controller(root,scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}