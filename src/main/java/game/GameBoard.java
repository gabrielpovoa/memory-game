package game;

import models.Card;
import models.NormalCard;
import java.util.*;

public class GameBoard {

    private List<Card> cards = new ArrayList<>();
    private int size;

    public GameBoard(int size) {
        this.size = size;
    }

    public void generateCards() {

        // LISTA MUTÁVEL (agora pode ser embaralhada)
        List<String[]> availableImages = new ArrayList<>(Arrays.asList(
                new String[]{"android", "src/main/resources/images/android.png"},
                new String[]{"b7", "src/main/resources/images/b7.png"},
                new String[]{"caminhao", "src/main/resources/images/caminhao.png"},
                new String[]{"disney", "src/main/resources/images/disney.png"},
                new String[]{"estrela", "src/main/resources/images/estrela.png"},
                new String[]{"gasolina", "src/main/resources/images/gasolina.png"},
                new String[]{"moto", "src/main/resources/images/moto.png"},
                new String[]{"restart", "src/main/resources/images/restart.png"}
        ));

        int totalPairs = (size * size) / 2;

        Collections.shuffle(availableImages); // agora funciona

        for (int i = 0; i < totalPairs; i++) {
            String[] img = availableImages.get(i);

            cards.add(new NormalCard(img[0], img[1]));
            cards.add(new NormalCard(img[0], img[1]));
        }

        System.out.println("Total de cartas criadas: " + cards.size());
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
        System.out.println("Cartas embaralhadas!");
    }

    public List<Card> getCards() {
        return cards;
    }
}
