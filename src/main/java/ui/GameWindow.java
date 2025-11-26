package ui;

import game.GameController;
import models.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameWindow extends JFrame {

    private GameController controller;
    private JPanel boardPanel;

    private JLabel scoreLabel;
    private JLabel timeLabel;

    private JButton startButton;
    private JButton pauseButton;
    private JButton backButton;

    private int size;

    public GameWindow(int size) {

        this.size = size;

        setTitle("Jogo da Memória");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(0x000000));
        setLayout(new BorderLayout());

        controller = new GameController(this, size);

        // ================================
        //     BARRA SUPERIOR
        // ================================
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 5));
        topPanel.setBackground(new Color(0x0A1931));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // BOTÃO INICIAR
        startButton = createButton("Iniciar");
        startButton.addActionListener(e -> startGame());

        // BOTÃO PAUSAR
        pauseButton = createButton("Pausar");
        pauseButton.addActionListener(e -> controller.togglePause());

        // BOTÃO VOLTAR
        backButton = createButton("Voltar");
        backButton.setBackground(new Color(0x660000));
        backButton.addActionListener(e -> controller.goBackToMenu());

        // LABELS
        scoreLabel = new JLabel("Pontuação: 0", SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        timeLabel = new JLabel("Tempo: 0s", SwingConstants.CENTER);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // ADICIONA ELEMENTOS
        topPanel.add(startButton);
        topPanel.add(pauseButton);
        topPanel.add(backButton);
        topPanel.add(scoreLabel);
        topPanel.add(timeLabel);

        add(topPanel, BorderLayout.NORTH);

        // ================================
        //       TABULEIRO
        // ================================
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(size, size, 12, 12));
        boardPanel.setBackground(new Color(0x000000));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ================================
    //  MÉTODO DE CRIAR BOTÕES PADRÃO
    // ================================
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(0x001F3F));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x454545)),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        return btn;
    }

    // ================================
    //  INICIAR NOVO JOGO
    // ================================
    public void startGame() {

        startButton.setEnabled(false); // DESABILITA INICIAR

        controller.startGame();

        boardPanel.removeAll();
        List<Card> cards = controller.getBoard().getCards();

        for (Card card : cards) {
            CardButton button = new CardButton(card);
            button.addActionListener(e -> controller.handleCardFlip(button));
            boardPanel.add(button);
        }

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    // ================================
    //  Funções para Interface
    // ================================
    public void updateScore(int score) {
        scoreLabel.setText("Pontuação: " + score);
    }

    public void updateTime(int seconds) {
        timeLabel.setText("Tempo: " + seconds + "s");
    }

    public void updatePauseButton(boolean paused) {
        pauseButton.setText(paused ? "Retomar" : "Pausar");
    }

    public void enableStartButton() {
        startButton.setEnabled(true);
    }
}
