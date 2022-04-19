package timers;

public class Timer7Sec implements Runnable{
    private Chronometer chrono;
    public Timer7Sec(Chronometer chrono) {
        this.chrono = chrono;
    }

    @Override
    public void run() {
        synchronized (chrono) {
            while (true) {
                try {
                    chrono.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int time = chrono.getTime();
                if (time % 7 == 0) {
                    System.out.println(time + " seconds left from another thread");
                }
                if (time == chrono.getEndTime()) {
                    break;
                }
            }
        }
    }
}
