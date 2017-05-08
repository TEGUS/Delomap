/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javabeans.TypePrestation;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class TypePrestationDAO extends DAO<TypePrestation>{

    @Override
    public void create(TypePrestation obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into typeprestation(code, nom) values(?,?)");
        st.setString(1, obj.getCode());
        st.setString(2, obj.getNom());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(TypePrestation obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("update typeprestation set nom=? where code=?");
        st.setString(1, obj.getNom());
        st.setString(2, obj.getCode());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(TypePrestation obj) throws SQLException {
        PreparedStatement del_proc = this.connect.prepareStatement("delete from typeprestation where code = ?");
        del_proc.setString(1, obj.getCode());
        del_proc.executeUpdate();
        del_proc.close();
    }

    public List<TypePrestation> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM typeprestation");
        ResultSet ret = st.executeQuery();
        
        List<TypePrestation> tps = new ArrayList();
        while (ret.next()) {
            TypePrestation tp = new TypePrestation(ret.getString("code"), ret.getString("nom"));
            tps.add(tp);
        }
        st.close();
        return tps;
    }

    @Override
    public TypePrestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
