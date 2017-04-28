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
import javabeans.TypeDocument;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class TypeDocumentDAO extends DAO<TypeDocument>{

    @Override
    public void create(TypeDocument obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into typedocument(code, nom, phase) values(?,?)");
        st.setString(1, obj.getCode());
        st.setString(2, obj.getNom());
        st.setString(2, obj.getPhase());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(TypeDocument obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("update typedocument set nom=?, phase=? where code=?");
        st.setString(1, obj.getNom());
        st.setString(2, obj.getPhase());
        st.setString(2, obj.getCode());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(TypeDocument obj) throws SQLException {
        PreparedStatement del_proc = this.connect.prepareStatement("delete from typedocument where code = ?");
        del_proc.setString(1, obj.getCode());
        del_proc.executeUpdate();
        del_proc.close();
    }

    @Override
    public List<TypeDocument> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM typedocument");
        ResultSet ret = st.executeQuery();

        List<TypeDocument> typeDocuments = new ArrayList();
        while (ret.next()) {
            TypeDocument a = new TypeDocument(
                ret.getString("code"), ret.getString("nom") , ret.getString("phase")
            );
            typeDocuments.add(a);
        }
        st.close();
        return typeDocuments;
    }

    @Override
    public TypeDocument findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
