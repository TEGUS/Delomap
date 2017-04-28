/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javabeans.Administration;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class AdministrationDAO extends DAO<Administration> {

    @Override
    public void create(Administration obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into administration(code, nom, type, adresseMail) values(?,?,?,?)");
        st.setString(1, obj.getCode());
        st.setString(2, obj.getNom());
        st.setString(3, obj.getType());
        st.setString(4, obj.getAdresseMail());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(Administration obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("update administration set nom=?, type=?, adresseMail=? where code=?");
        st.setString(1, obj.getNom());
        st.setString(2, obj.getType());
        st.setString(3, obj.getAdresseMail());
        st.setString(4, obj.getCode());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(Administration obj) throws SQLException {
        PreparedStatement del_proc = this.connect.prepareStatement("delete from administration where code = ?");
        del_proc.setString(1, obj.getCode());
        del_proc.executeUpdate();
        del_proc.close();
    }

    @Override
    public List<Administration> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM administration");
        ResultSet ret = st.executeQuery();

        List<Administration> administrations = new ArrayList();
        while (ret.next()) {
            Administration a = new Administration(
                    ret.getString("code"), ret.getString("nom"), ret.getString("type"), ret.getString("adresseMail")
            );
            administrations.add(a);
        }
        st.close();
        return administrations;
    }

    @Override
    public Administration findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
