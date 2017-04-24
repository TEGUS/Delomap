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

/**
 *
 * @author Aurelien KOUAM
 */
public class MarcheDAO extends DAO<Marche> {

    @Override
    public void create(Marche marche) {
        //mettre à jour la table marche
        Integer idMarche = null;
        try {
            PreparedStatement st = this.connect.prepareStatement("insert into marche(Nom, DateDebut, DateFin, Montant) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, marche.getNom());
            st.setTimestamp(2, new Timestamp(marche.getDateDebut().getTime()));
            st.setTimestamp(3, new Timestamp(marche.getDateFin().getTime()));
            st.setInt(4, marche.getMontant());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                idMarche = rs.getInt(1);
            }
            rs.close();
            st.close();
            
            String typePrestation = marche.getCodeTypePrestation();
            //mettre à jour les tables procedures
            st = this.connect.prepareStatement("SELECT * FROM procedurepartypeprestation where codeTypePrestation = ?");
            st.setString(1, typePrestation);
            ResultSet ret = st.executeQuery();
//System.out.println("typeP" + typePrestation);
            while (ret.next()) {
//System.out.println("test");
                String tpro = ret.getString("codeTypeProcedure");

                PreparedStatement stp = this.connect.prepareStatement("insert into procedures(procedurepartypeprestation_codeTypePrestation, procedurepartypeprestation_codeTypeProcedure, marche_idMarche) VALUES (?, ?, ?)");
                stp.setString(1, typePrestation);
                stp.setString(2, tpro);
                stp.setInt(3, idMarche);
                stp.executeUpdate();
                stp.close();
            }
            st.close();

            //mettre à jour les tables document
            st = this.connect.prepareStatement("SELECT * FROM type_v where typeprestation = ? and typedocument is not null and typedocument != ''");
            st.setString(1, typePrestation);
            ret = st.executeQuery();

            while (ret.next()) {
                String tpro = ret.getString("typeprocedure");
                String tdo = ret.getString("typedocument");

                PreparedStatement stp = this.connect.prepareStatement("insert into document(documentpartypeprocedure_codeTypeProcedure, documentpartypeprocedure_codeTypeDocument, idMarche) VALUES (?, ?, ?)");
                stp.setString(1, tpro);
                stp.setString(2, tdo);
                stp.setInt(3, idMarche);
                stp.executeUpdate();
                stp.close();
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        PreparedStatement st = this.connect.prepareStatement("SELECT * FROM marche_v");
        ResultSet ret = st.executeQuery();

        List<Marche> marches = new ArrayList();
        while (ret.next()) {
//            System.out.println("montant : "+ret.getString("Montant")+" type prestation : "+ret.getString("procedurepartypeprestation_codeTypePrestation"));
            Marche m = new Marche();
            m.setId(ret.getInt("id"));
            m.setNom(ret.getString("Nom"));
            m.setDateDebut(ret.getDate("DateDebut"));
            m.setDateFin(ret.getDate("DateFin"));
            m.setAdministration(new Administration(ret.getString("CodeAdministration")));
            m.setMontant(ret.getInt("Montant"));
            m.setCodeTypePrestation(ret.getString("procedurepartypeprestation_codeTypePrestation"));
            marches.add(m);
        }
        st.close();
        return marches;
    }

    public List<Marche> findAll(String nom, String TypePrestation) throws SQLException {
        String filterTypePrestation = "";
        if (!TypePrestation.equals("")) {
            filterTypePrestation += " and procedurepartypeprestation_codeTypePrestation = '" + TypePrestation + "'";
        }
        String sqlQuery = "SELECT * FROM marche_v where (Nom LIKE '%" + nom + "%' or Montant LIKE '%" + nom + "%' or DateDebut LIKE '%" + nom + "%' or DateFin LIKE '%" + nom + "%')" + filterTypePrestation;
        //System.out.println(sqlQuery);
        PreparedStatement st = this.connect.prepareStatement(sqlQuery);
        ResultSet ret = st.executeQuery();

        List<Marche> marches = new ArrayList();
        while (ret.next()) {
            Marche m = new Marche();
            m.setId(ret.getInt("id"));
            m.setNom(ret.getString("Nom"));
            m.setDateDebut(ret.getDate("DateDebut"));
            m.setDateFin(ret.getDate("DateFin"));
            m.setAdministration(new Administration(ret.getString("CodeAdministration")));
            m.setMontant(ret.getInt("Montant"));
            m.setCodeTypePrestation(ret.getString("procedurepartypeprestation_codeTypePrestation"));

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
