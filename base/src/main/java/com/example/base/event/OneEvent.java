package com.example.base.event;

public class OneEvent {
    public String name;


    public OneEvent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OneEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
