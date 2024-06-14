import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        setBackground(new Color(241, 242, 215));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setFocusable(true);

        // background
        JPanel bckgrnd = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bckgrndImg = new ImageIcon(new File("resources/images/bckgrnd.png").getPath());
                g.drawImage(bckgrndImg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bckgrnd.setLayout(null);

        // logo
        JLabel logo = new JLabel(new ImageIcon(new File("resources/images/logo.gif").getPath()));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // buttons
        JLabel playButton = button("resources/images/defaultPlayBtn.png", "resources/images/hoverPlayBtn.png", new GamePanel());
        JLabel controlButton = button("resources/images/defaultCtrlBtn.png", "resources/images/hoverCtrlBtn.png", new ControlPanel());

        add(Box.createRigidArea(new Dimension(0, 30)));
        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(playButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(controlButton);
        add(bckgrnd);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }

    public JLabel button(String defaultPath, String hoverPath, JPanel switchTo) {
        ImageIcon defaultIcon = new ImageIcon(defaultPath);
        ImageIcon hoverIcon = new ImageIcon(hoverPath);

        JLabel button = new JLabel(defaultIcon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(defaultIcon);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                GameControl.switchPanels(MenuPanel.this, switchTo);
            }
        });

        return button;
    }
}
