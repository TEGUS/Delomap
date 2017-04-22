/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delomap;

import java.sql.SQLException;
import javabeans.Marche;
import pattern.dao.DAO;
import pattern.factory.DAOFactory;

/**
 *
 * @author Aurelien KOUAM
 */
public class Delomap {

    
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DAO<Marche> dao = DAOFactory.getMarcheDAO();
        System.out.println(dao.findAll());
    }
    
}
