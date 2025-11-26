package game;

import models.Card;
import ui.CardButton;
import ui.GameWindow;
import ui.StartScreen;
import storage.SaveManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private GameWindow window;
    private GameBoard board;

    private int size;

    private List<CardButton> flipped = new ArrayList<>();

    private int score = 0;
    private int correctPairs = 0;
    private int totalPairs;

    private int seconds = 0;
    private Timer timer;

    private boolean paused = false;

    public GameController(GameWindow window, int size) {
        this.window = window;
        this.size = size;
        this.totalPairs = (size * size) / 2;
    }

    public void startGame() {
        score = 0;
        correctPairs = 0;
        seconds = 0;

        board = new GameBoard(size);
        board.generateCards();
        board.shuffleCards();

        startTimer();
        window.updateScore(score);
        window.updateTime(seconds);
    }

    public GameBoard getBoard() {
        return board;
    }

    // TIMER
    private void startTimer() {
        if (timer != null) timer.stop();

        timer = new Timer(1000, e -> {
            if (!paused) {
                seconds++;
                window.updateTime(seconds);
            }
        });

        timer.start();
    }

    // PAUSA
    public void togglePause() {
        paused = !paused;

        if (paused) {
            JOptionPane.showMessageDialog(null, "Jogo pausado!");
        }
    }

    // VOLTAR PARA O MENU
    public void goBackToMenu() {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja voltar ao menu?",
                "Confirmar saída",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            if (timer != null) timer.stop();
            window.dispose();
            new StartScreen();
        }
    }

    // CLICK NA CARTA
    public void handleCardFlip(CardButton button) {

        if (paused) return;

        Card card = button.getCard();

        if (card.isRevealed()) return;
        if (flipped.size() == 2) return;

        card.flip();
        button.refresh();
        flipped.add(button);

        if (flipped.size() == 2) {
            checkMatch();
        }
    }

    // VERIFICAR PAR
    private void checkMatch() {
        CardButton c1 = flipped.get(0);
        CardButton c2 = flipped.get(1);

        if (c1.getCard().getValue().equals(c2.getCard().getValue())) {

            score += 10;
            correctPairs++;

            window.updateScore(score);
            flipped.clear();

            checkWin();
        } else {

            Timer t = new Timer(800, e -> {
                c1.getCard().flip();
                c2.getCard().flip();
                c1.refresh();
                c2.refresh();
                flipped.clear();
            });

            t.setRepeats(false);
            t.start();
        }
    }

    // VITÓRIA
    private void checkWin() {
        if (correctPairs == totalPairs) {
            timer.stop();

            // 🏆 SALVA O PLACAR
            storage.SaveManager.saveScore(score, seconds);

            // Exibe popup de vitória
            JOptionPane.showMessageDialog(
                    null,
                    "Parabéns! Você venceu!\nPontuação: " + score + "\nTempo: " + seconds + "s",
                    "Vitória!",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
