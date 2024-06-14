import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        setBackground(new Color(241, 242, 215));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setFocusable(true);

        JLabel logo = new JLabel(new ImageIcon(new File("resources/images/ctrlLogo.png").getPath()));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ctrls = new JLabel(new ImageIcon(new File("resources/images/ctrls.png").getPath()));
        ctrls.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(ctrls);
        add(Box.createRigidArea(new Dimension(0, 80)));
        add(backButton());

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
    }

    private JLabel backButton() {
        ImageIcon dfBackBtn = new ImageIcon(new File("resources/images/defaultBackBtn.png").getPath());
        ImageIcon hvBackBtn = new ImageIcon(new File("resources/images/hoverBackBtn.png").getPath());

        JLabel backbtn = new JLabel(dfBackBtn);
        backbtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        backbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backbtn.setIcon(hvBackBtn);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                backbtn.setIcon(dfBackBtn);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                GameControl.switchPanels(ControlPanel.this, new MenuPanel());
            }
        });
        return backbtn;
    }
}
