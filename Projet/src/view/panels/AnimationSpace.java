package view.panels;

import threads.ThreadMovement;

import javax.swing.*;
import java.awt.*;

public class AnimationSpace extends JPanel {
    // public static final  int B_WIDTH = 350;
    public static final int B_HEIGHT = 350;
    public static final int INITIAL_X = -40;
    public static final int INITIAL_Y = -40;

    private ImageIcon planeIcon;
    private Image planeImg;
    private static int x, y;


    public AnimationSpace() {
        planeIcon = new ImageIcon("src/resources/plane.png");
        planeImg = planeIcon.getImage();

        new ThreadMovement(this).start();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        x = INITIAL_X;
        y = INITIAL_Y;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(planeImg, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    public void cycle() {
        x++;
        y++;

        if (y > B_HEIGHT) {
            y = INITIAL_Y;
            x = INITIAL_X;
        }
    }
        /*private void cycle() {
            while(!isUp){
                x++;
                y++;
            }


            if(y == B_HEIGHT + 1){
                x = B_WIDTH+40;
                y = B_HEIGHT+40;
                isUp = true;
            }

            while(isUp){
                x--;
                y--;
            }

            if(y == INITIAL_Y){
                isUp = false;
            }
        }*/
}
