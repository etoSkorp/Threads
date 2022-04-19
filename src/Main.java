import parser.Parser;
import parser.ParserScanner;
import producerConsumer.Consumer;
import producerConsumer.Producer;
import timers.Chronometer;
import timers.Timer5Sec;
import timers.Timer7Sec;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        /*
        Задания по потокам
         */
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(producer);
        Future future = service.submit(consumer);
        service.shutdown();
        try {
            future.get(2L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            future.cancel(true);
        }

        System.out.println(consumer.getTotal());


        /*
        Задание с хронометром и таймерами
         */
        Chronometer chrono = new Chronometer();
        Timer5Sec timer5Sec = new Timer5Sec(chrono);
        Timer7Sec timer7Sec = new Timer7Sec(chrono);

        new Thread(chrono).start();
        new Thread(timer5Sec).start();
        new Thread(timer7Sec).start();
    }
}