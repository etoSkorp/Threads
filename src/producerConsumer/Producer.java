package producerConsumer;

import parser.ParserScanner;
import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private ParserScanner parserScanner = new ParserScanner();
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        File file = new File("voyna.txt");

        for (String elem : parserScanner.parse(file)) {
            if (elem.matches("^[Сс]трада(.*)")) {
                try {
                    queue.put(elem);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
