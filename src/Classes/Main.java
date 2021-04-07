package Classes;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Comparison obj = new Comparison(50000);
                obj.compareTime();
            }
        });
    }
}
