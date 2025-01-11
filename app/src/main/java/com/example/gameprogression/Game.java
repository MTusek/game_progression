package com.example.gameprogression;
public class Game {
    private String name;
    private int cover;
    private boolean isFavorite;

    private String description;

    public Game(String name, String description, int cover, boolean isFavorite) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

