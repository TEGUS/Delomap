/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.sql.Blob;

/**
 *
 * @author Aurelien KOUAM
 */
public class Document {
    private int id;
    private String nom;
    private String delaiTransmission;
    private String statut;
    private Blob archive;
    private DocumentParTypeProcedure documentParTypeProcedure;
    private Marche marche;
    private String phase;

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDelaiTransmission() {
        return delaiTransmission;
    }

    public void setDelaiTransmission(String delaiTransmission) {
        this.delaiTransmission = delaiTransmission;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getStatut() {
        return statut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Blob getArchive() {
        return archive;
    }

    public void setArchive(Blob archive) {
        this.archive = archive;
    }

    public DocumentParTypeProcedure getDocumentParTypeProcedure() {
        return documentParTypeProcedure;
    }

    public void setDocumentParTypeProcedure(DocumentParTypeProcedure documentParTypeProcedure) {
        this.documentParTypeProcedure = documentParTypeProcedure;
    }

    public Marche getMarche() {
        return marche;
    }

    public void setMarche(Marche marche) {
        this.marche = marche;
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", nom=" + nom + ", delaiTransmission=" + delaiTransmission + ", statut=" + statut + ", archive=" + archive + ", documentParTypeProcedure=" + documentParTypeProcedure + ", marche=" + marche + ", phase=" + phase + '}';
    }
    
}
