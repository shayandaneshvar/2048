package model;

import view.Observer;

public interface Observable {
    void addObserver(Observer observer);

    void updateAllObservers();
}
