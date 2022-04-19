package producerConsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private int total = 0;
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

        @Override
    public void run() {
        while (true) {
            try {
                String word = queue.take();
                System.out.println(word);
                total++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getTotal() {
        return total;
    }
}
