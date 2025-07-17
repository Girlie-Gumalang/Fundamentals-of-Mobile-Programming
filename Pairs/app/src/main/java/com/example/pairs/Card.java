package com.example.pairs;

public class Card {
    public int imageResId;
    public boolean isMatched = false;
    public boolean isFlipped = false;

    public Card(int imageResId) {
        this.imageResId = imageResId;
    }
}
