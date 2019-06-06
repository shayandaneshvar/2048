package view;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Board;
import model.Observable;

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

        for(int i = 0;i<board.getBoard().length;i++){
            for(int j=0;j<board.getBoard()[0].length;j++){
                StackPane stack = new StackPane();
                Rectangle cell = new Rectangle(200,200, Color.rgb(255,
                        board.getBoard()[i][j]%256,
                        255 - board.getBoard()[i][j]%256));
//                cell.setOpacity(board.getBoard()[i][j]/2048d);
                cell.setStroke(Color.BLACK);
                cell.setArcHeight(20);
                cell.setArcWidth(20);
                Label label = new Label(" ");
                label.setScaleX(1.5);
                label.setScaleY(1.5);
                label.setFont(Font.font("Arial Black",25));
                if(board.getBoard()[i][j]!=0){
                    label.setText(String.valueOf(board.getBoard()[i][j]));
                }
                stack.getChildren().addAll(cell,label);
                boardGame.add(stack,i,j,1,1);
            }
        }
    }


    @Override
    public void update(Observable changedObservable) {
        drawBoard((Board)changedObservable);
    }


//    @Override
//    public void run() {
//        //todo
//    }
}
