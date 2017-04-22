/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;


/**
 *
 * @author Aurelien KOUAM
 */
public class Procedure {
    private int id;
    private String statut;
    private ProcedureParTypePrestation procedureParTypePrestation;
    private Marche marche;

    public Procedure() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public ProcedureParTypePrestation getProcedureParTypePrestation() {
        return procedureParTypePrestation;
    }

    public void setProcedureParTypePrestation(ProcedureParTypePrestation procedureParTypePrestation) {
        this.procedureParTypePrestation = procedureParTypePrestation;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }
    
}
