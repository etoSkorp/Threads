import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        /*
        Задание 1
        */
        File file = new File("voyna.txt");
        Parser parser = new Parser();
        ParserScanner parserScanner = new ParserScanner();
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
    }
}