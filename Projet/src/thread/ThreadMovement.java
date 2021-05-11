package thread;

import view.panels.menuWindowPanels.AnimationSpace;

public class ThreadMovement extends Thread {
    public final int DELAY = 10;

    private AnimationSpace animationSpace;

    public ThreadMovement(AnimationSpace animationSpace) {
        super("ThreadMovement");
        this.animationSpace = animationSpace;
    }

    @Override
    public void run() {
        long currentTimeSystem, timeDiff, sleep;

        currentTimeSystem = System.currentTimeMillis();

        while (true) {
            animationSpace.cycle();
            animationSpace.repaint();

            timeDiff = System.currentTimeMillis() - currentTimeSystem;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

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
