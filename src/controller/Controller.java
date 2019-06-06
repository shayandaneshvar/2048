package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import model.Board;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Controller {
    private Board board;
    private View view;
    private KeyCode keyPressed;

    public Controller(Group root, Scene scene) {
        int[][] myBoard = new int[4][4];
        this.view = new View(root, scene);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                myBoard[i][j] = 0;
            }
        }

        board = new Board(myBoard);
        board.addObserver(view);
        view.drawBoard(board);
        keyPressed = KeyCode.SPACE;
        scene.setOnKeyPressed(event -> {
            keyPressed = event.getCode();
            try {
                if (!board.isGameOver()) {
                    handleModel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleModel() throws IOException {
//        Platform.runLater(() -> {
        switch (keyPressed) {
            case UP:
                board.handleSwipeUp();
                break;
            case DOWN:
                board.handleSwipeDown();
                break;
            case LEFT:
                board.handleSwipeLeft();
                break;
            case RIGHT:
                board.handleSwipeRight();
                break;
            case ENTER:
                //restart The Program
            default:
                InputStream inputStream = new FileInputStream("kicked.wav");
                AudioStream audioStream = new AudioStream(inputStream);
                AudioPlayer.player.start(audioStream);
        }
        System.out.println(Arrays.deepToString(board.getBoard()));
        System.out.println(Arrays.toString(board.getBoard()[0]));
        System.out.println(Arrays.toString(board.getBoard()[1]));
        System.out.println(Arrays.toString(board.getBoard()[2]));
        System.out.println(Arrays.toString(board.getBoard()[3]));
        keyPressed = KeyCode.SPACE;
//        });
    }
}
