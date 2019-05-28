package view;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Observable;

public class View extends Application implements Observer, Runnable {
    private GridPane root;
    // FIXME: 5/29/2019 

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new GridPane();
        //todo
    }

    @Override
    public void update(Observable changedObservable) {
        //todo
    }

    @Override
    public void run() {
        //todo
    }
}
