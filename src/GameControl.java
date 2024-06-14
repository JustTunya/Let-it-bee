import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameControl implements ActionListener {
    private final GamePanel panel;
    private final Character character;
    private final Obstacle obstacle;
    private final Background background;

    private boolean isStarted = false;
    private static boolean isPaused = false;
    private static boolean isOver = false;
    private static boolean isMuted = false;
    private static int score = 0;

    private Clip ambience;

    public GameControl(GamePanel panel, Character character, Obstacle obstacle, Background background) {
        this.panel = panel;
        this.character = character;
        this.obstacle = obstacle;
        this.background = background;

        new Timer(25, this).start();

        try {
            ambience = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/ambience.wav"));
            ambience.open(audioInputStream);
            ambience.loop(Clip.LOOP_CONTINUOUSLY);
            ambience.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isPaused && !isOver) {
            background.update();
            character.update();

            if (isStarted) {
                character.active();
                obstacle.update();

                if (character.getY() < 0 || character.getY() + Character.getDiameter() > panel.getHeight() - 70 || obstacle.collides(character)) {
                    HighScore.highscore(score);
                    isOver = true;
                    sound("gameover");
                }

                // Update score
                if (obstacle.passed(character)) {
                    score++;
                    sound("score");
                }
            }
        }

        panel.repaint();
    }

    public void pause() {
        isPaused = !isPaused;
    }

    public void mute() {
        if (isMuted) {
            ambience.loop(Clip.LOOP_CONTINUOUSLY);
            ambience.start();
        } else {
            ambience.stop();
        }

        isMuted = !isMuted;
    }

    public void start() {
        isStarted = true;
    }

    public void restart() {
        score = 0;

        isStarted = false;
        isPaused = false;
        isOver = false;

        character.reset();
        obstacle.clear();
        background.clear();

        obstacle.set();
        background.set();

        panel.repaint();
    }

    public static void sound(String file) {
        if (!isMuted) {
            try {
                AudioInputStream jump = AudioSystem.getAudioInputStream(new File("resources/sounds/" + file + ".wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(jump);
                clip.start();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void switchPanels(JPanel current, JPanel switchTo) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(current);
            if (frame != null) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(switchTo);
                frame.revalidate();
                switchTo.requestFocusInWindow();
            } else {
                System.err.println("Error: Couldn't find JFrame ancestor.");
            }
        });
    }

    public static boolean isOver() {
        return !isOver;
    }
    public static boolean isPaused() {
        return isPaused;
    }

    public static int getScore() {
        return score;
    }
}
