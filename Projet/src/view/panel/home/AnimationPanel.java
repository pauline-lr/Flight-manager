package view.panel.home;

import thread.ThreadMovement;

import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    public static final int POS_INIT_X = -10;
    public static final int POS_INIT_Y = 0;

    private ImageIcon planeIcon;
    private Image planeImg;
    private int x;

    public AnimationPanel() {
        planeIcon = new ImageIcon("src/resources/plane.png");
        planeImg = planeIcon.getImage();

        new ThreadMovement(this).start();
        setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

        x = POS_INIT_X;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(planeImg, x, POS_INIT_Y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    public void cycle() {
        x++;

        if (x > this.getWidth()){
            x = POS_INIT_X;
        }
    }
}
