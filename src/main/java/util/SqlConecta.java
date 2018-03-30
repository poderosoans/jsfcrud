/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author JAVA_LM
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConecta {

    private final String db;

    public SqlConecta() {
        db = "trabajofinal";
    }

    public Connection connection() {
        Connection cn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + db, "root", "mysql");
            
        } catch (SQLException sqle) {
          sqle.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ERROR Class NF"+cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.out.println("ERROR Init Exc"+ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR Illegal acc ex"+iae.getMessage());
        }
        
        return cn;
    }
}
