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
import javabeans.Marche;
import java.util.List;
import javabeans.Administration;
import javabeans.Prestation;
import javabeans.TypePrestation;

/**
 *
 * @author Aurelien KOUAM
 */
public class MarcheDAO extends DAO<Marche> {

    @Override
    public void create(Marche obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Marche obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Marche obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marche> findAll() throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM marche_view");
        ResultSet ret = st.executeQuery();
        
        List<Marche> marches = new ArrayList();
        while (ret.next()) {
            Marche m = new Marche();
            m.setId(ret.getInt("idMarche"));
            m.setNom(ret.getString("NomMarche"));
            m.setDateDebut(ret.getDate("DateDebut"));
            m.setDateFin(ret.getDate("DateFin"));
            m.setAdministration(new Administration(ret.getString("CodeAdministrationConcernee")));
            
            Prestation p = new Prestation(ret.getInt("IdPrestation"));
            p.setTypePrestation(new TypePrestation(ret.getString("codeTypePrestation")));
            
            m.setPrestation(p);
            m.setMontant(ret.getInt("Montant"));
            
            marches.add(m);
        }
        st.close();
        return marches;
    }

    public List<Marche> findAll(String nom, String TypePrestation) throws SQLException {
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM marche_view where (NomMarche LIKE '%"+nom+"%' or Montant LIKE '%"+nom+"%' or DateDebut LIKE '%"+nom+"%' or DateFin LIKE '%"+nom+"%') and codeTypePrestation = '"+TypePrestation+"'");
        ResultSet ret = st.executeQuery();
        
        List<Marche> marches = new ArrayList();
        while (ret.next()) {
            Marche m = new Marche();
            m.setId(ret.getInt("idMarche"));
            m.setNom(ret.getString("NomMarche"));
            m.setDateDebut(ret.getDate("DateDebut"));
            m.setDateFin(ret.getDate("DateFin"));
            m.setAdministration(new Administration(ret.getString("CodeAdministrationConcernee")));
            
            Prestation p = new Prestation(ret.getInt("IdPrestation"));
            p.setTypePrestation(new TypePrestation(ret.getString("codeTypePrestation")));
            
            m.setPrestation(p);
            m.setMontant(ret.getInt("Montant"));
            
            marches.add(m);
        }
        st.close();
        return marches;
    }

    @Override
    public Marche findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
