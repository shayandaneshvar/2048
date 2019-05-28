package view;

import model.Observable;

public interface Observer {
    void update(Observable changedObservable);
}
