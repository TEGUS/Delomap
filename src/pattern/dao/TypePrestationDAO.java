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
    public void create(TypePrestation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TypePrestation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TypePrestation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TypePrestation> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT code FROM typeprestation");
        ResultSet ret = st.executeQuery();
        
        List<TypePrestation> tps = new ArrayList();
        while (ret.next()) {
            TypePrestation tp = new TypePrestation();
            tp.setCode(ret.getString("code"));
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
