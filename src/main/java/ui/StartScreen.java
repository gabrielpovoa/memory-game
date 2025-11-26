package ui;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {
    private JButton startButton;
    private JButton pauseButton;

    public StartScreen() {
        setTitle("Menu - Jogo da Memória");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0x000000));

        // LOGO
        JLabel logo = new JLabel("MEMORY GAME", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        logo.setForeground(Color.WHITE);
        logo.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        add(logo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(0x000000));
        centerPanel.setLayout(new GridLayout(4, 1, 10, 10));

        // BOTÕES DO MENU
        JButton level4 = createMenuButton("Nível 4x4");

        // ações
        level4.addActionListener(e -> openGame(4));

        centerPanel.add(level4);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(0x001F3F));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return btn;
    }

    private void openGame(int size) {
        dispose();
        new GameWindow(size);
    }
}
