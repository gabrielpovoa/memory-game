package models;

public class SpecialCard extends Card {

    public SpecialCard(String value) {
        super(value);
    }

    public SpecialCard(String value, String imagePath) {
        super(value, imagePath);
    }

    @Override
    public void flip() {
        revealed = !revealed;
        System.out.println("Carta especial virou: " + value);
    }
}
