/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aurelien KOUAM
 */
public class ConnectionMySQL {

    public static Connection getInstanceConnection() {
        String port = "3306";
        String dbName = "delomapdb";
        String user = "root";
        String password = "";
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + dbName, "" + user, "" + password);
        } catch (ClassNotFoundException cne) {
            System.out.println("Impossible de charger le driver");
            System.out.println(cne);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion");
            e.printStackTrace();
        }
        return con;
    }

    public static void getCloseConnection() {
        Connection con = getInstanceConnection();
        try {
            con.close();
            System.out.println("Fermeture de la connexion");
        } catch (SQLException ex) {
            System.out.println("Erreur de fermeture.");
        }
    }
}
