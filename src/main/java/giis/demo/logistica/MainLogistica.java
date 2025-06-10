package giis.demo.logistica;

import giis.demo.logistica.view.MainView;
import giis.demo.util.Database;

import javax.swing.*;

public class MainLogistica {

    public static void main(String[] args) {
        Database db = new Database();

        db.createDatabase(false);
        db.loadDatabase();

        System.out.println("Base de datos creada");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainView();

                frame.setVisible(true);
            }
        });

    }

}
