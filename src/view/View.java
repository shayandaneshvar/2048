package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Observable;

public class View extends Application implements Observer/*, Runnable*/ {
    private StackPane root;
    private GridPane boardGame;
    // FIXME: 5/29/2019 

    public View() {
        this.root = new StackPane();
        this.boardGame = new GridPane();
        root.getChildren().add(boardGame);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //todo
        primaryStage.setTitle("2048");
        Scene scene = new Scene(root, 800, 800, Color.WHEAT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update(Observable changedObservable) {
        //todo
    }

//    @Override
//    public void run() {
//        //todo
//    }
}
