/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.PreparedStatement;
import javabeans.Administration;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class AdministrationDAO extends DAO<Administration> {

    @Override
    public void create(Administration obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into administration(codeAdministration, nomAdministration, typeAdministration, adresseMail) values(?,?,?,?)");
        st.setString(1, obj.getCode());
        st.setString(2, obj.getNom());
        st.setString(3, obj.getType());
        st.setString(4, obj.getAdresseMail());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(Administration obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Administration obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Administration> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Administration findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
