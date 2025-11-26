package ui;

import models.Card;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardButton extends JButton {

    private Card card;

    public CardButton(Card card) {
        this.card = card;

        setIcon(card.getImage());

        // Layout moderno
        setBackground(new Color(25, 25, 25));
        setForeground(Color.WHITE);
        setFocusPainted(false);

        // Tamanho
        setPreferredSize(new Dimension(120, 120));

        // Remove estilo padrão do JButton
        setContentAreaFilled(false);
        setOpaque(true);

        // 🔵 Bordas arredondadas modernas
        setBorder(new RoundedBorder(18));

        // Hover elegante
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBorder(new RoundedBorderHighlight(18, new Color(0x0099FF)));
                setBackground(new Color(35, 35, 35));
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBorder(new RoundedBorder(18));
                setBackground(new Color(25, 25, 25));
                repaint();
            }
        });
    }

    // =====================================================
    //              ANIMAÇÃO DE FLIP (SEM ALTERAÇÕES)
    // =====================================================
    public void refresh() {
        animateFlip();
    }

    private void animateFlip() {
        Timer shrink = new Timer(15, null);
        final int[] width = {getWidth()};
        final int[] step = {0};

        shrink.addActionListener(e -> {
            step[0]++;
            int newW = width[0] - step[0] * 8;

            if (newW <= 0) {
                newW = 0;
                shrink.stop();
                setIcon(card.getImage());
                expand();
            }

            setPreferredSize(new Dimension(newW, getHeight()));
            revalidate();
        });

        shrink.start();
    }

    private void expand() {
        Timer expand = new Timer(15, null);
        final int[] width = {0};

        expand.addActionListener(e -> {
            width[0] += 8;

            if (width[0] >= 120) {
                width[0] = 120;
                expand.stop();
            }

            setPreferredSize(new Dimension(width[0], getHeight()));
            revalidate();
        });

        expand.start();
    }

    public Card getCard() {
        return card;
    }
}

// =====================================================
//               BORDAS ARREDONDADAS BONITAS
// =====================================================

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(radius+2, radius+2, radius+2, radius+2);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(70, 70, 70));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x+1, y+1, w-3, h-3, radius, radius);
    }
}

class RoundedBorderHighlight implements Border {
    private int radius;
    private Color color;

    RoundedBorderHighlight(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(radius+2, radius+2, radius+2, radius+2);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x+1, y+1, w-3, h-3, radius, radius);
    }

}
