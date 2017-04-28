/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javabeans.Procedure;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class ProcedureDAO extends DAO<Procedure> {

    @Override
    public void create(Procedure obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Procedure obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Procedure obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Procedure> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Procedure> findByMarche(int idMarche) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM procedures");
        ResultSet ret = st.executeQuery();
        
        List<Procedure> procedures = new ArrayList();
        while (ret.next()) {
            Procedure p = new Procedure();
            p.setId(ret.getInt("id"));
            p.setStatut(ret.getString("statut"));
            procedures.add(p);
        }
        st.close();
        return procedures;
    }

    @Override
    public Procedure findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
