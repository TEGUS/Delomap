/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class Procedure {
    private int id;
    private String statut;
    private List<Document> documents;
    private Prestation prestation;
    private TypeProcedure typeProcedure;

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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public TypeProcedure getTypeProcedure() {
        return typeProcedure;
    }

    public void setTypeProcedure(TypeProcedure typeProcedure) {
        this.typeProcedure = typeProcedure;
    }
    
    
}
