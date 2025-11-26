package models;

public class NormalCard extends Card {

    // Construtor antigo (mantém compatibilidade)
    public NormalCard(String value) {
        super(value);
    }

    // Construtor novo com imagem
    public NormalCard(String value, String imagePath) {
        super(value, imagePath);
    }

    @Override
    public void flip() {
        revealed = !revealed;
    }
}
