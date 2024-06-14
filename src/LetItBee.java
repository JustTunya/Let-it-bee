import javax.swing.*;

public class LetItBee extends JFrame {
    private static final int width = 800;
    private static final int height = 600;

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new MenuPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Let It Bee");
        frame.setIconImage(new ImageIcon("resources/images/icon.png").getImage());
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
