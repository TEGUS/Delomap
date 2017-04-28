/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javabeans.Document;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class DocumentDAO extends DAO<Document> {

    @Override
    public void create(Document obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Document obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Document obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Document> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Document findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Document> findByMarche(int idMarche) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM document");
        ResultSet ret = st.executeQuery();
        
        List<Document> documents = new ArrayList();
        while (ret.next()) {
            Document d = new Document();
            d.setId(ret.getInt("id"));
            d.setDelaiTransmission(ret.getTimestamp("DelaisTransmission"));
            d.setStatut(ret.getString("statut"));
            documents.add(d);
        }
        st.close();
        return documents;
    }
    
}
