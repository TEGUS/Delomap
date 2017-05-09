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
import javabeans.Document;
import java.util.List;
import javabeans.Marche;

/**
 *
 * @author Aurelien KOUAM
 */
public class DocumentDAO extends DAO<Document> {

    @Override
    public void create(Document obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into document(nom, delaisTransmission, phase, statut, idMarche) values(?,?,?,?,?)");
        st.setString(1, obj.getNom());
        st.setString(2, obj.getDelaiTransmission());
        st.setString(3, obj.getPhase());
        st.setString(4, obj.getStatut());
        st.setInt(5, obj.getMarche().getId());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(Document obj) throws SQLException {
        System.out.println(obj);
        PreparedStatement st = this.connect.prepareStatement("update document set nom=?, DelaisTransmission=?, phase=?, Statut=?, idMarche=? where id=?");
        st.setString(1, obj.getNom());
        st.setString(2, obj.getDelaiTransmission());
        st.setString(3, obj.getPhase());
        st.setString(4, obj.getStatut());
        st.setInt(5, obj.getMarche().getId());
        st.setInt(6, obj.getId());
        st.executeUpdate();
        st.close();
    }
    
    public void update(Integer id) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("update document set Statut='envoye' where id=?");
        st.setInt(1, id);
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(Document obj) throws SQLException {
        PreparedStatement del_proc = this.connect.prepareStatement("delete from document where id=?");
        del_proc.setInt(1, obj.getId());
        del_proc.executeUpdate();
        del_proc.close();
    }

    @Override
    public List<Document> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM document");
        ResultSet ret = st.executeQuery();
        
        List<Document> documents = new ArrayList();
        while (ret.next()) {
            Document d = new Document();
            d.setId(ret.getInt("id"));
            d.setDelaiTransmission(ret.getString("DelaisTransmission"));
            d.setNom(ret.getString("nom"));
            d.setPhase(ret.getString("phase"));
            d.setStatut(ret.getString("statut"));
            d.setMarche(new MarcheDAO().findById(ret.getInt("idMarche")));
            documents.add(d);
        }
        st.close();
        return documents;
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
            d.setNom(ret.getString("nom"));
            d.setDelaiTransmission(ret.getString("DelaisTransmission"));
            d.setStatut(ret.getString("statut"));
            d.setPhase(ret.getString("phase"));
            documents.add(d);
        }
        st.close();
        return documents;
    }
    
}
