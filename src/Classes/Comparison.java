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

    public static void compareMethods(int repeat){

        System.out.println("Method                 "+"Iteration "+"Time for ArrayList "+"time for LinkedList");

        System.out.print("add in the end      ");
        getTime(Comparison::getTimeAdd, repeat, 1);

        System.out.print("add in the beginning ");
        getTime(Comparison::getTimeAdd, repeat, 0);

        System.out.print("delete from the middle ");
        getTime(Comparison::getTimeRemove, repeat, 0.5);

        System.out.print("get                ");
        getTime(Comparison::getTimeGet, repeat, 1);

        System.out.print("set                   ");
        getTime(Comparison::getTimeSet, repeat, 1);

        System.out.print("sort                  ");
        System.out.print("1 ");
        getTime(Comparison::getTimeSort, repeat, 1);

    }

    public static void getTime(Methods function, int repeat, double k){
        long time1, time2;
        List<Integer> firstCollection = new ArrayList<Integer>();
        List<Integer> secondCollection = new LinkedList<Integer>();
        for (int i=0; i<repeat; i++){
            firstCollection.add(i, i);
            secondCollection.add(i, i);
        }
        time1 = function.apply(firstCollection, repeat, k);
        time2 = function.apply(secondCollection, repeat, k);
        System.out.println(Integer.toString(repeat)+" "+String.valueOf(time1)+" "+String.valueOf(time2));
    }

    /**
     * Метод, сравнивающий время работы метода ADD при добавлении объектов
     */

    private static long getTimeAdd(List<Integer> collection, int repeat, double k){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.add((int)(i*k), i);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }


    /**
     * Метод, сравнивающий время выполнения метода REMOVE при удалении из середины коллекции
     */

    private static long getTimeRemove (List<Integer> collection, int repeat, double k){
        Instant start, finish;
        long time;
        start = Instant.now();
        for (int i=0; i<repeat; i++){
            collection.remove(i*k);
        }
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }

    /**
     * Метод, сравнивающий время выполнения метода GET
     */

    private static long getTimeGet (List<Integer> collection, int repeat, double k){
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

    private static long getTimeSet (List<Integer> collection, int repeat, double k){
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

    private static long getTimeSort (List<Integer> collection, int repeat, double k){
        Instant start, finish;
        long time;
        start = Instant.now();
        Collections.sort(collection);
        finish = Instant.now();
        time = Duration.between(start, finish).toMillis();
        return time;
    }



}
