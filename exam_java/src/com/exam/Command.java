package com.exam;

public abstract class Command {
    private boolean isReady = false;
    public boolean isReady() {
        return isReady;
    }
    void setReady(boolean ready) {
        isReady = ready;
    }
    public abstract void run(String... args);
}
