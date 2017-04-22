/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
    public void create(Marche marche) throws SQLException {
        //mettre à jour la table marche
        Integer idMarche = null;
        
        PreparedStatement st = this.connect.prepareStatement("insert into marche(Nom, DateDebut, DateFin, Montant) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, marche.getNom());
        st.setTimestamp(2, new Timestamp(marche.getDateDebut().getTime()));
        st.setTimestamp(3, new Timestamp(marche.getDateFin().getTime()));
        st.setInt(4, marche.getMontant());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()){
            idMarche=rs.getInt(1);
        }
        rs.close();
        st.close();
        
        String typePrestation = marche.getCodeTypePrestation();
        //mettre à jour les tables procedures
        st = this.connect.prepareStatement("SELECT * FROM procedurepartypeprestation where codeTypePrestation = ?");
        st.setString(1, typePrestation);
        ResultSet ret = st.executeQuery();
        
        while (ret.next()) {
            String tpro = ret.getString("codeTypeProcedure");
            
            PreparedStatement stp = this.connect.prepareStatement("insert into procedures(procedurepartypeprestation_codeTypePrestation, procedurepartypeprestation_codeTypeProcedure, marche_idMarche) VALUES (?, ?, ?)");
            stp.setString(1, typePrestation);
            stp.setString(1, tpro);
            stp.setInt(1, idMarche);
            stp.executeUpdate();
            stp.close();
        }
        st.close();
        
        //mettre à jour les tables document
        st = this.connect.prepareStatement("SELECT * FROM type_v where typeprestation = ?");
        st.setString(1, typePrestation);
        ret = st.executeQuery();
        
        while (ret.next()) {
            String tpro = ret.getString("typeprocedure");
            String tdo = ret.getString("typedocument");
            
            PreparedStatement stp = this.connect.prepareStatement("insert into document(documentpartypeprocedure_codeTypeProcedure, documentpartypeprocedure_codeTypeDocument, idMarche) VALUES (?, ?, ?)");
            stp.setString(1, tpro);
            stp.setString(1, tdo);
            stp.setInt(1, idMarche);
            stp.executeUpdate();
            stp.close();
        }
        st.close();
        
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
        String filterTypePrestation = "";
        if (!TypePrestation.equals("")) {
            filterTypePrestation += " and codeTypePrestation = '"+TypePrestation+"'";
        }
        String sqlQuery = "SELECT * FROM marche_view where (NomMarche LIKE '%"+nom+"%' or Montant LIKE '%"+nom+"%' or DateDebut LIKE '%"+nom+"%' or DateFin LIKE '%"+nom+"%')"+filterTypePrestation;
        //System.out.println(sqlQuery);
        PreparedStatement st = this.connect.prepareStatement(sqlQuery);
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
