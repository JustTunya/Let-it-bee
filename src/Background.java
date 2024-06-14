import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Background {
    private final JPanel panel;
    private final List<Integer> speed;
    private final List<BufferedImage> images;
    private final List<Integer> x;

    public Background(JPanel panel) {
        this.panel = panel;
        this.x = new ArrayList<>();
        this.images = new ArrayList<>();
        this.speed = new ArrayList<>();
        set();
    }

    public void clear() {
        images.clear();
        speed.clear();
        x.clear();
    }

    public void set() {
        IntStream.range(0, 6).forEach(i -> {
            x.add(0);
            speed.add(i);
            try {
                images.add(ImageIO.read(new File("resources/images/background/" + (i + 1) + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
                images.add(null);
            }
        });
    }

    public void update() {
        for (int i = 0; i < x.size(); i++) {
            x.set(i, x.get(i) - speed.get(i));

            if (x.get(i) <= -images.get(i).getWidth()) {
                x.set(i, x.get(i) + images.get(i).getWidth());
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < x.size(); i++) {
            if (images.get(i) != null && x.get(i) + panel.getWidth() > 0 && x.get(i) < panel.getWidth()) {
                g.drawImage(images.get(i), x.get(i), -35, null);
                g.drawImage(images.get(i), x.get(i) + panel.getWidth(), -35, null);
            }
        }
    }
}