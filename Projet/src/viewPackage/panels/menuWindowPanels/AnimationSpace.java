package viewPackage.panels.menuWindowPanels;

import businessPackage.ThreadMovement;

import javax.swing.*;
import java.awt.*;

public class AnimationSpace extends JPanel {
    public static final  int PANEL_WIDTH = 500;
    //public static final int PANEL_HEIGHT = 350;
    public static final int POS_INIT_X = -10;
    public static final int POS_INIT_Y = 0;


    private ImageIcon planeIcon;
    private Image planeImg;
    private static int x, y;


    public AnimationSpace() {
        planeIcon = new ImageIcon("src/resources/plane.png");
        planeImg = planeIcon.getImage();

        new ThreadMovement(this).start();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        x = POS_INIT_X;
        y = POS_INIT_Y;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(planeImg, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    public void cycle() {
        x++;

        if (x > PANEL_WIDTH)
            x = POS_INIT_X;
    }
}
