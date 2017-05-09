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
import java.util.List;
import javabeans.FicheSuivi;

/**
 *
 * @author Aurelien KOUAM
 */
public class FicheSuiviDAO extends DAO<FicheSuivi> {

    @Override
    public void create(FicheSuivi obj) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("insert into ficheSuivi(designation, montant, dateReception, dateLancement, motif, observation) values(?,?,?,?,?,?)");
        st.setString(1, obj.getDesignation());
        st.setInt(2, obj.getMontant());
        st.setTimestamp(3, new Timestamp(obj.getDateReception().getTime()));
        st.setTimestamp(4, new Timestamp(obj.getDateLancement().getTime()));
        st.setString(5, obj.getMotif());
        st.setString(6, obj.getObservation());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update(FicheSuivi obj) throws SQLException {
        System.out.println(obj);
        PreparedStatement st = this.connect.prepareStatement("update ficheSuivi set designation=?, montant=?, dateReception=?, dateLancement=?, motif=?, observation=? where id=?");
        st.setString(1, obj.getDesignation());
        st.setInt(2, obj.getMontant());
        st.setTimestamp(3, new Timestamp(obj.getDateReception().getTime()));
        st.setTimestamp(4, new Timestamp(obj.getDateLancement().getTime()));
        st.setString(5, obj.getMotif());
        st.setString(6, obj.getObservation());
        st.setInt(7, obj.getId());
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete(FicheSuivi obj) throws SQLException {
        PreparedStatement del_proc = this.connect.prepareStatement("delete from ficheSuivi where id = ?");
        del_proc.setInt(1, obj.getId());
        del_proc.executeUpdate();
        del_proc.close();
    }

    @Override
    public List<FicheSuivi> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM ficheSuivi");
        ResultSet ret = st.executeQuery();
        
        List<FicheSuivi> ficheSuivis = new ArrayList();
        while (ret.next()) {
            FicheSuivi ficheSuivi = new FicheSuivi(
                ret.getInt("id"), 
                ret.getString("designation"),
                ret.getInt("montant"),
                ret.getDate("dateReception"),
                ret.getDate("dateLancement"),
                ret.getString("motif"),
                ret.getString("observation")
            );
            ficheSuivis.add(ficheSuivi);
        }
        st.close();
        return ficheSuivis;
    }

    @Override
    public FicheSuivi findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
