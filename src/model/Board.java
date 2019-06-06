package model;

import view.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board implements Observable {
    private int[][] board;
    private int score;
    private List<Observer> observers;
    private boolean isGameOver;

    public Board(int[][] board) {
        observers = new ArrayList<>();
        this.board = board;
        this.score = 0;
        isGameOver = false;
        generateNewNumber(board[0].length,board);
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

    public void handleSwipeRight() {
        boolean hasBoardChanged = false;
        boolean temp = false;
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[0].length - 1; i++) {
                temp = handleEastBlock(j, i);
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


    public void handleSwipeLeft() {
        boolean hasBoardChanged = false;
        boolean temp = false;
        for (int j = 0; j < board.length; j++) {
            for (int i = 0; i < board[0].length - 1; i++) {
                temp = handleWestBlock(j, board.length - i - 1);
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

    public boolean isGameOver()
    {
        return isGameOver;
    }

    public void handleSwipeDown() {
        boolean hasBoardChanged = false;
        boolean temp = false;
        for (int j = 0; j < board.length - 1; j++) {
            for (int i = 0; i < board[0].length; i++) {
                temp = handleSouthBlock(j, i);
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
        if (board[yCoord][xCoord] == board[yCoord - 1][xCoord]) {
            board[yCoord - 1][xCoord] *= 2;
            board[yCoord][xCoord] = 0;
            score += board[yCoord - 1][xCoord];
            return true;
        }
        return false;
    }

    private boolean handleSouthBlock(int yCoord, int xCoord) {
        if (board[yCoord][xCoord] == board[yCoord + 1][xCoord]) {
            board[yCoord + 1][xCoord] *= 2;
            board[yCoord][xCoord] = 0;
            score += board[yCoord + 1][xCoord];
            return true;
        }
        return false;

    }

    private boolean handleWestBlock(int yCoord, int xCoord) {
        if (board[yCoord][xCoord] == board[yCoord][xCoord - 1]) {
            board[yCoord][xCoord - 1] *= 2;
            board[yCoord][xCoord] = 0;
            score += board[yCoord][xCoord - 1];
            return true;
        }
        return false;
    }

    private boolean handleEastBlock(int yCoord, int xCoord) {
        if (board[yCoord][xCoord] == board[yCoord][xCoord + 1]) {
            board[yCoord][xCoord + 1] *= 2;
            board[yCoord][xCoord] = 0;
            score += board[yCoord + 1][xCoord];
            return true;
        }
        return false;
    }

    private static void generateNewNumber(int length, int[][] board) {
        Random random = new Random();
        int xAxis, yAxis;
        do {
            xAxis = Math.abs(random.nextInt()) % length;
            yAxis = Math.abs(random.nextInt()) % length;
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
