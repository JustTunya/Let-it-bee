import javax.swing.*;
import java.awt.*;

public class GameOver {
    private final JPanel panel;
    private final Graphics g;

    public GameOver(JPanel panel, Graphics g) {
        this.panel = panel;
        this.g = g;
    }

    public void set() {
        int w = panel.getWidth() / 2, h = panel.getHeight() / 2;

        new ImageIcon("resources/images/menu.png").paintIcon(null, g, 200, 100);

        g.setColor(new Color(24, 20, 37));
        drawText("Game Over", 50f, panel.getWidth() / 2, panel.getHeight() / 2 - 100);
        drawText("Score  " + GameControl.getScore(), 30f, w, h - 70);
        drawText("High Score  " + HighScore.getHighScore(), 30f, w, h - 40);

        g.setColor(new Color(115, 62, 57));
        drawText("Press R to restart", 30f, w, h + 40);
    }

    private void drawText(String text, float fontSize, int x, int y) {
        g.setFont(g.getFont().deriveFont(fontSize));
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        g.drawString(text, x - textWidth / 2, y);
    }
}