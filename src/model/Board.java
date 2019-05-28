package model;

import view.Observer;

import java.util.List;
import java.util.Random;

public class Board implements Observable {
    private int[][] board;
    private int score;
    private List<Observer> observers;
    private boolean isGameOver;

    public Board(int[][] board) {
        this.board = board;
        this.score = 0;
        isGameOver = false;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void handleSwipeUp() {
        boolean hasBoardChanged = false;
        boolean temp = false;
        for (int j = 0; j < board.length - 1; j++) {
            for (int i = 0; i < board[0].length; i++) {
                temp = handleNorthBlock(board[0].length - 1 - j, i);
                if (temp) {
                    hasBoardChanged = true;
                }
            }
        }
        if (hasBoardChanged && !isGameOver(board)) {
            generateNewNumber(board[0].length, board);
        } else if (!hasBoardChanged) {
            isGameOver = true;
        }
        updateAllObservers();
    }

    // FIXME: 5/29/2019 
    public void handleSwipeRight() {
        // TODO: 5/29/2019   
        updateAllObservers();
    }

    // FIXME: 5/29/2019 
    public void handleSwipeLeft() {
        // TODO: 5/29/2019  
        updateAllObservers();
    }

    // FIXME: 5/29/2019 
    public void handleSwipeDown() {
        // TODO: 5/29/2019  
        updateAllObservers();
    }

    private static boolean isGameOver(int[][] board) {
        //Note that this is a hard one - can change it later
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] < 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean handleNorthBlock(int yCoord, int xCoord) {
        if (board[yCoord][xCoord] == board[yCoord - 1][xCoord - 1]) {
            board[yCoord - 1][xCoord - 1] *= 2;
            board[yCoord][xCoord] = 0;
            return true;
        }
        return false;
    }

    private static void generateNewNumber(int length, int[][] board) {
        Random random = new Random();
        int xAxis, yAxis;
        do {
            xAxis = random.nextInt() % length;
            yAxis = random.nextInt() % length;
            if (!isOccupied(yAxis, xAxis, board)) {
                board[yAxis][xAxis] = 2;
                break;
            }
        } while (true);
    }

    private static boolean isOccupied(int yAxis, int xAxis, int[][] board) {
        if (board[yAxis][xAxis] < 2) {
            return false;
        }
        return true;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void updateAllObservers() {
        observers.stream().forEach(x -> x.update(this));
    }
}
