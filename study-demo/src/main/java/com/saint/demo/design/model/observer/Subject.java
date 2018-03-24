package com.saint.demo.design.model.observer;

public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notify(String message);
}
