package com.example.gameprogression;

public class Game {
    private final String name;
    private final int cover;

    public Game(String name, int cover) {
        this.name = name;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public int getCover() {
        return cover;
    }
}
