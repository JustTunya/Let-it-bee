import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class GamePanel extends JPanel {
    private static final int width = 800;
    private static final int height = 600;

    private final Character character;
    private final Obstacle obstacle;
    private final Background background;
    private final GameControl controller;

    public GamePanel() {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);

        character = new Character(this, width / 4, height / 2);
        background = new Background(this);
        obstacle = new Obstacle(this);

        controller = new GameControl(this, character, obstacle, background);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_P && GameControl.isOver()) {
                    controller.pause();
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    controller.restart();
                } else if (e.getKeyCode() == KeyEvent.VK_M) {
                    controller.mute();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                    if (GameControl.isOver() && !GameControl.isPaused()) {
                        controller.start();
                        character.jump();
                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g);
        obstacle.draw(g);
        character.draw(g);

        File fontFile = new File("resources/font/DigitalDisco.ttf");

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            g.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GameControl.isOver()) {
            g.setColor(new Color(24, 20, 37));
            g.drawString("Score  " + GameControl.getScore(), 30, 30);
        } else {
            GameOver go = new GameOver(this, g);
            go.set();
        }

        if (GameControl.isPaused()) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(0, 0, getWidth(), getHeight());

            ImageIcon pauseTitle = new ImageIcon(new File("resources/images/pauseLogo.png").getPath());
            g.drawImage(pauseTitle.getImage(), getWidth() / 2 - pauseTitle.getIconWidth() / 2, getHeight() / 2 - pauseTitle.getIconHeight() / 2, this);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
