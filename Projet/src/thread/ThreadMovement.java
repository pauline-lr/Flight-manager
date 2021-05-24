package thread;

import view.panel.home.AnimationPanel;

public class ThreadMovement extends Thread {
    public final int DELAY = 10;

    private AnimationPanel animationPanel;

    public ThreadMovement(AnimationPanel animationPanel) {
        super("ThreadMovement");
        this.animationPanel = animationPanel;
    }

    @Override
    public void run() {
        long currentTimeSystem, timeDiff, sleep;

        currentTimeSystem = System.currentTimeMillis();

        while (true) {
            animationPanel.cycle();
            animationPanel.repaint();

            timeDiff = System.currentTimeMillis() - currentTimeSystem;
            sleep = DELAY - timeDiff;

            /*if (sleep < 0) {
                sleep = 2;
            }*/

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch(java.util.ConcurrentModificationException e){
                e.printStackTrace();
            }

            currentTimeSystem = System.currentTimeMillis();
        }
    }
}
