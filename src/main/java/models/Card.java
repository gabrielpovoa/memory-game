package models;

import javax.swing.*;
import java.awt.*;

public abstract class Card {

    protected String value;
    protected boolean revealed = false;

    protected Icon frontImage;
    protected static Icon backImage = scaleImage("src/main/resources/images/back.png", 100, 100);

    public Card(String value) {
        this.value = value;
    }

    public Card(String value, String imagePath) {
        this.value = value;
        this.frontImage = scaleImage(imagePath, 100, 100);
    }

    public abstract void flip();

    public Icon getImage() {
        return revealed ? frontImage : backImage;
    }

    public String getValue() {
        return value;
    }

    public boolean isRevealed() {
        return revealed;
    }

    // Método para redimensionar imagens
    protected static Icon scaleImage(String path, int w, int h) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
