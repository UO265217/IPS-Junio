package giis.demo.logistica;

import giis.demo.util.Database;

public class MainLogistica {

    public static void main(String[] args) {
        Database db = new Database();

        db.createDatabase(false);
        db.loadDatabase();

        System.out.println("Base de datos creada");
    }

}
