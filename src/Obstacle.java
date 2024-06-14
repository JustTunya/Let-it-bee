import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacle {
    private final JPanel panel;
    private static final int gap = 150, speed = 6, distance = 300;
    private final List<BufferedImage> obstacles = new ArrayList<>();
    private final List<Integer> x = new ArrayList<>(), y = new ArrayList<>();

    public Obstacle(JPanel panel) {
        this.panel = panel;
        set();
    }

    private int randY() {
        return new Random().nextInt(panel.getHeight() - 2 * gap) + gap / 2;
    }

    public void clear() {
        obstacles.clear();
        x.clear();
        y.clear();
    }

    public void set() {
        for (int i = 0; i < 6; i++) {
            try { obstacles.add(ImageIO.read(new File("resources/images/obstacle.png"))); }
            catch (IOException e) { e.printStackTrace(); }
            x.add(panel.getWidth() + i * distance);
            y.add(randY());
        }
    }

    public void update() {
        for (int i = 0; i < x.size(); i++) {
            x.set(i, x.get(i) - speed);
            if (x.get(i) <= -obstacles.get(i).getWidth())
                x.set(i, x.get(i == 0 ? x.size() - 1 : i - 1) + distance);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < x.size(); i++) {
            g.drawImage(obstacles.get(i), x.get(i), y.get(i) - obstacles.get(i).getHeight(), null);
            g.drawImage(obstacles.get(i), x.get(i), y.get(i) + gap, null);
        }
    }

    public boolean passed(Character character) {
        return x.stream().anyMatch(xi -> character.getX() == xi + obstacles.get(x.indexOf(xi)).getWidth());
    }

    public boolean collides(Character character) {
        return x.stream().anyMatch(xi ->
                character.getX() + Character.getDiameter() > xi &&
                        character.getX() < xi + obstacles.get(x.indexOf(xi)).getWidth() &&
                        (character.getY() < y.get(x.indexOf(xi)) || character.getY() + Character.getDiameter() > y.get(x.indexOf(xi)) + gap)
        );
    }
}