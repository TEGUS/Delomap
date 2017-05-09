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
    private String codeTypePrestation;
    private String codeTypeProcedure;
    private Marche marche;

    public String getCodeTypePrestation() {
        return codeTypePrestation;
    }

    public void setCodeTypePrestation(String codeTypePrestation) {
        this.codeTypePrestation = codeTypePrestation;
    }

    public String getCodeTypeProcedure() {
        return codeTypeProcedure;
    }

    public void setCodeTypeProcedure(String codeTypeProcedure) {
        this.codeTypeProcedure = codeTypeProcedure;
    }

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

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    @Override
    public String toString() {
        return codeTypeProcedure;
    }
    
}
