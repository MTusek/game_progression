package com.example.gameprogression;
public class Game {
    private String name;
    private int cover;
    private boolean isFavorite;

    public Game(String name, int cover, boolean isFavorite) {
        this.name = name;
        this.cover = cover;
        this.isFavorite = isFavorite;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCover() {
        return cover;
    }
    public void setCover(int cover) {
        this.cover = cover;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

