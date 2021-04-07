package Classes;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Класс, содержащий функционал для сравнения времени работы тех или иных методов
 * для ArrayList и LinkedList
 * Используется расширение, позволяющее выводить данные в виде таблицы
 * @author Губина Алина
 */
public class Comparison extends JFrame {

    static String[][] data;
    static int position;
    /*Сравниваемые коллекции*/
    static ArrayList<Integer> firstCollection;
    static LinkedList<Integer> secondCollection;

    /**
     * Конструктор по-умолчанию
     */
    Comparison (){
        firstCollection = new ArrayList<Integer>();
        secondCollection = new LinkedList<Integer>();
        data = new String[7][];
        for (int i=0; i<7; i++)
            data[i]=new String[4];
        position = 0;
    }

    /**
     * Метод, сравнивающий время работы метода ADD при добавлении объектов в конец коллекции
     */
    static void compareAddInTheEnd(){
        Instant start, finish;
        long time1, time2;
        start = Instant.now();
        for (int i=0; i<15000; i++){
            firstCollection.add(i, i);
        }
        finish = Instant.now();
        time1 = Duration.between(start, finish).toMillis();

        start = Instant.now();
        for (int i=0; i<15000; i++){
            secondCollection.add(i, i);
        }
        finish = Instant.now();
        time2 = Duration.between(start, finish).toMillis();
        data[position][0]="add in the end";
        data[position][1]="15000";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    /**
     * Метод, сравнивающий время работы метода ADD при добавление в начало коллекции
     */
    static void compareAddInTheBeginning(){
        Instant start, finish;
        long time1, time2;
        start = Instant.now();
        for (int i=0; i<15000; i++){
            firstCollection.add(0, i);
        }
        finish = Instant.now();
        time1 = Duration.between(start, finish).toMillis();

        start = Instant.now();
        for (int i=0; i<15000; i++){
            secondCollection.add(0, i);
        }
        finish = Instant.now();
        time2 = Duration.between(start, finish).toMillis();
        data[position][0]="add in the beginning";
        data[position][1]="15000";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    /**
     * Метод, сравнивающий время выполнения метода REMOVE при удалении из середины коллекции
     */
    static void compareDeleteFromTheMiddle(){
        Instant start, finish;
        long time1, time2;
        start = Instant.now();
        for (int i=0; i<15000; i++){
            firstCollection.remove(i/2);
        }
        finish = Instant.now();
        time1 = Duration.between(start, finish).toMillis();

        start = Instant.now();
        for (int i=0; i<15000; i++){
            secondCollection.remove(i/2);
        }
        finish = Instant.now();
        time2 = Duration.between(start, finish).toMillis();
        data[position][0]="delete from the middle";
        data[position][1]="15000";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    /**
     * Метод, сравнивающий время выполнения метода GET
     */
    static void compareGetTheMiddle(){
        Instant start, finish;
        long time1, time2;
        start = Instant.now();
        for (int i=0; i<15000; i++){
            firstCollection.get(i);
        }
        finish = Instant.now();
        time1 = Duration.between(start, finish).toMillis();

        start = Instant.now();
        for (int i=0; i<15000; i++){
            secondCollection.get(i);
        }
        finish = Instant.now();
        time2 = Duration.between(start, finish).toMillis();
        data[position][0]="get";
        data[position][1]="15000";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    /**
     *Метод, сравнивающий время выполнения метода SET
     */
    static void compareSet(){
        Instant start, finish;
        long time1, time2;
        start = Instant.now();
        for (int i=0; i<15000; i++){
            firstCollection.set(i, 150000-i);
        }
        finish = Instant.now();
        time1 = Duration.between(start, finish).toMillis();

        start = Instant.now();
        for (int i=0; i<15000; i++){
            secondCollection.set(i, 150000-i);
        }
        finish = Instant.now();
        time2 = Duration.between(start, finish).toMillis();
        data[position][0]="set";
        data[position][1]="15000";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    /**
     * Метод, сравнивающий время при сортировке коллекций
     */
    static void compareSort(){
        Instant start, finish;
        long time1, time2;
        start = Instant.now();

        finish = Instant.now();
        time1 = Duration.between(start, finish).toMillis();
        Collections.sort(firstCollection);
        start = Instant.now();
        Collections.sort(secondCollection);
        finish = Instant.now();
        time2 = Duration.between(start, finish).toMillis();
        data[position][0]="sort";
        data[position][1]="1";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    /**
     * Метод, собирающий данные о времени выполнения различных методов
     */
    static void compareTime(){
        ArrayList<Integer> firstCollection = new ArrayList<Integer>();
        LinkedList<Integer> secondCollection = new LinkedList<Integer>();
        JFrame frame = new JFrame("Time for methods");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {
                "Method",
                "Iteration",
                "Time for ArrayList",
                "Time for LinkedList"
        };

        compareAddInTheEnd();
        compareAddInTheBeginning();
        compareDeleteFromTheMiddle();
        compareGetTheMiddle();
        compareSet();
        compareSort();

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
