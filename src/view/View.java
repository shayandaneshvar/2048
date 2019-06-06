package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Board;
import model.Observable;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class View implements Observer/*, Runnable*/ {
    private Group root;
    private Scene scene;
    private GridPane boardGame;

    public View(Group root, Scene scene) {
        this.root = root;
        this.scene = scene;
        this.boardGame = new GridPane();
        root.getChildren().add(boardGame);
        boardGame.setPadding(new Insets(20));
        boardGame.setHgap(20);
        boardGame.setVgap(20);
    }

    public void drawBoard(Board board) {
        boardGame.getChildren().clear();

        for (int j = 0; j < board.getBoard().length; j++) {
            for (int i = 0; i < board.getBoard()[0].length; i++) {
                StackPane stack = new StackPane();
                Rectangle cell = new Rectangle(200, 200, Color.rgb(205,
                        board.getBoard()[i][j] % 200 + 56,
                        100 - board.getBoard()[i][j] % 100));
//                cell.setOpacity(board.getBoard()[i][j]/2048d);
                cell.setStroke(Color.BLACK);
                cell.setArcHeight(20);
                cell.setArcWidth(20);
                Label label = new Label(" ");
                label.setScaleX(1.5);
                label.setScaleY(1.5);
                label.setFont(Font.font("Arial Black", 25));
                if (board.getBoard()[i][j] != 0) {
                    label.setText(String.valueOf(board.getBoard()[i][j]));
                }
                stack.getChildren().addAll(cell, label);
                boardGame.add(stack, j, i, 1, 1);
            }
            Label label =
                    new Label("Score :" + board.getScore());
            label.setStyle("-fx-background-color: blue;-fx-text-fill: White");
            root.getChildren().add(label);
        }
    }


    @Override
    public void update(Observable changedObservable) throws IOException {
        drawBoard((Board) changedObservable);
        if (((Board) changedObservable).isGameOver()) {
            System.out.println("Game Over");
            int score = ((Board) changedObservable).getScore();
            System.out.println(((Board) changedObservable).getScore());
            root.getChildren().clear();
            VBox vBox = new VBox();
            Text gameOver = new Text("Game Over");
            gameOver.setFont(Font.font("Monospaced", 100));
            Text scoreText = new Text(String.valueOf(score));
            Label ssd = new Label("Made By S.Shayan Daneshvar");
            scoreText.setFont(Font.font("Monospaced", 100));
            ssd.setFont(Font.font("Gothic", 20));
            ssd.setPadding(new Insets(100));
            vBox.getChildren().addAll(gameOver, scoreText, ssd);
            root.getChildren().add(vBox);
            vBox.setAlignment(Pos.CENTER);
            vBox.setPadding(new Insets(180));
            InputStream inputStream = new FileInputStream("benny1.wav");
            AudioStream audioStream = new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        }
    }
//    @Override
//    public void run() {
//        //todo
//    }
}