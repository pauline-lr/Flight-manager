package businessPackage;

import viewPackage.panels.menuWindowPanels.AnimationSpace;

public class ThreadMovement extends Thread {
    public final int DELAY = 20;

    private AnimationSpace animationSpace;

    public ThreadMovement(AnimationSpace animationSpace) {
        super("ThreadMovement");
        this.animationSpace = animationSpace;
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {
            animationSpace.cycle();
            animationSpace.repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
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

            beforeTime = System.currentTimeMillis();
        }
    }
}
