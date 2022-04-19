package timers;

import static java.lang.Thread.sleep;
public class Chronometer implements Runnable{
    private final int endTime = 15;
    private int time = 0;
    private boolean checkTime;

    @Override
    public void run() {
        while (time < endTime) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            time++;
            System.out.println(time + " seconds left");
            setTime(time);
        }
    }
    public synchronized void setTime(int time) {
        checkTime = true;
        this.time = time;
        checkTime = false;
        notifyAll();
    }
    public synchronized int getTime() {
        while (checkTime) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return time;
    }
    public int getEndTime() {
        return endTime;
    }
}
