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
import javabeans.TypeProcedure;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class TypeProcedureDAO extends DAO<TypeProcedure>{

    @Override
    public void create(TypeProcedure obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into typeprocedure(code, nom) values(?,?)");
        st.setString(1, obj.getCode());
        st.setString(2, obj.getNom());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(TypeProcedure obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("update typeprocedure set nom=? where code=?");
        st.setString(1, obj.getNom());
        st.setString(2, obj.getCode());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(TypeProcedure obj) throws SQLException {
        PreparedStatement del_proc = this.connect.prepareStatement("delete from typeprocedure where code = ?");
        del_proc.setString(1, obj.getCode());
        del_proc.executeUpdate();
        del_proc.close();
    }

    @Override
    public List<TypeProcedure> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM typeprocedure");
        ResultSet ret = st.executeQuery();

        List<TypeProcedure> typeProcedures = new ArrayList();
        while (ret.next()) {
            TypeProcedure a = new TypeProcedure(
                ret.getString("code"), ret.getString("nom")
            );
            typeProcedures.add(a);
        }
        st.close();
        return typeProcedures;
    }

    @Override
    public TypeProcedure findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
