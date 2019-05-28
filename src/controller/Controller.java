package controller;

import model.Board;
import view.View;


public class Controller {
    private Board board;
    private View view;

    public Controller() {
        int[][] myBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                myBoard[i][j] = 0;
            }
        }
        view = new View();
        board = new Board(myBoard);
        board.addObserver(view);
    }
//todo
    //fixme
}
