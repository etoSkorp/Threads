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

        File file = new File("voyna.txt");

        /*
        Задание 1
         */
        Parser parser = new Parser();
        ArrayList<String> sufferingWords = new ArrayList<>();

        for (String elem : parser.parse(file)) {
            if (elem.matches("^[Сс]трада(.*)")) {
                sufferingWords.add(elem);
                System.out.println(elem);
            }
        }
        System.out.println(sufferingWords.size());

        /*
        Задание 2
         */
        ParserScanner parserScanner = new ParserScanner();
        ArrayList<String> sufferingWordsScanner = new ArrayList<>();

        for (String elem : parserScanner.parse(file)) {
            if (elem.matches("^[Сс]трада(.*)")) {
                sufferingWordsScanner.add(elem);
                System.out.println(elem);
            }
        }
        System.out.println(sufferingWordsScanner.size());

        /*
        Задание 3
         */
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (j == 1)
                    System.out.printf("%2d", i * j);  // Привет из python :)
                else
                    System.out.printf("%4d", i * j);  // Это, возможно, лишнее, но зато красиво :)
            }
            System.out.println();
        }


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