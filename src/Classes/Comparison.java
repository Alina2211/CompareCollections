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

    String[][] data;
    int position;
    int repeat;
    /*Сравниваемые коллекции*/
    List<Integer> firstCollection; /*ArrayList*/
    List<Integer> secondCollection; /*LinkedList*/

    /**
     * Конструктор по-умолчанию
     */
    Comparison (int n){
        firstCollection = new ArrayList<Integer>();
        secondCollection = new LinkedList<Integer>();
        data = new String[7][];
        for (int i=0; i<7; i++)
            data[i]=new String[4];
        position = 0;
        repeat = n;
        
    }

    /**
     * Метод, сравнивающий время работы метода ADD при добавлении объектов в конец коллекции
     */
    void compareAddInTheEnd(){
        long time1, time2;
        time1 = getTimeAddInTheEnd(firstCollection);
        time2 = getTimeAddInTheEnd(secondCollection);
        data[position][0]="add in the end";
        data[position][1]=Integer.toString(repeat);
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    long getTimeAddInTheEnd (List<Integer> collection){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.add(i, i);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     * Метод, сравнивающий время работы метода ADD при добавление в начало коллекции
     */
    void compareAddInTheBeginning(){
        long time1, time2;
        time1 = getTimeAddInTheBeginning(firstCollection);
        time2 = getTimeAddInTheBeginning(secondCollection);
        data[position][0]="add in the beginning";
        data[position][1]=Integer.toString(repeat);
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }
    long getTimeAddInTheBeginning (List<Integer> collection){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.add(0, i);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     * Метод, сравнивающий время выполнения метода REMOVE при удалении из середины коллекции
     */
    void compareDeleteFromTheMiddle(){
        long time1, time2;
        time1 = getTimeRemove(firstCollection);
        time2 = getTimeRemove(secondCollection);
        data[position][0]="delete from the middle";
        data[position][1]=Integer.toString(repeat);
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    long getTimeRemove (List<Integer> collection){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.remove(i/2);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     * Метод, сравнивающий время выполнения метода GET
     */
    void compareGetTheMiddle(){
        long time1, time2;
        time1 = getTimeGet(firstCollection);
        time2 = getTimeGet(secondCollection);
        data[position][0]="get";
        data[position][1]=Integer.toString(repeat);
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    long getTimeGet (List<Integer> collection){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.get(i);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     *Метод, сравнивающий время выполнения метода SET
     */
    void compareSet(){
        long time1, time2;
        time1 = getTimeSet(firstCollection);
        time2 = getTimeSet(secondCollection);
        data[position][0]="set";
        data[position][1]=Integer.toString(repeat);
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    long getTimeSet (List<Integer> collection){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.set(i, 150000-i);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     * Метод, сравнивающий время при сортировке коллекций
     */
    void compareSort(){
        long time1, time2;
        time1 = getTimeSort(firstCollection);
        time2 = getTimeSort(secondCollection);
        data[position][0]="sort";
        data[position][1]="1";
        data[position][2]=String.valueOf(time1);
        data[position][3]=String.valueOf(time2);
        position++;
    }

    long getTimeSort (List<Integer> collection){
        Instant start, finish;
        long time;
        start = Instant.now();
        Collections.sort(collection);
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     * Метод, собирающий данные о времени выполнения различных методов
     */
    void compareTime(){
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
