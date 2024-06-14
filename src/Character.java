import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Character {
    private final JPanel panel;
    private static final int jumpPower = 18;
    private static final int diameter = 36;

    private final int x;
    private int y;
    private int velocity;
    private int gravity;

    private final Image bee;

    public Character(JPanel panel, int x, int y) {
        this.panel = panel;
        this.x = x; this.y = y;
        this.velocity = 0;
        this.gravity = 0;

        bee = new ImageIcon(new File("resources/images/bee.gif").getPath()).getImage();
    }

    public void jump() {
        this.velocity = -jumpPower;
        GameControl.sound("jump");
    }

    public void update() {
        this.velocity += gravity;
        this.y += this.velocity;
    }

    public void rotate(Graphics g, int angle) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(angle), x - 10 + (double) bee.getWidth(null) / 2, y + (double) bee.getHeight(null) / 2);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        rotate(g, 3 * velocity / 4);
        g2d.drawImage(bee, x - 10, y, null);
        rotate(g, -3 * velocity / 4);
    }

    public void reset() {
        this.y = panel.getHeight() / 2;
        this.velocity = 0;
        this.gravity = 0;
    }

    public void active() {
        this.gravity = 2;
    }

    public static int getDiameter() {
        return diameter;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
