package view;

import model.Observable;

import java.io.IOException;

public interface Observer {
    void update(Observable changedObservable) throws IOException;
}