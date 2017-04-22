/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Aurelien KOUAM
 */
public class Document {
    private int id;
    private Date delaiTransmission;
    private String statut;
    private Blob archive;
    private DocumentParTypeProcedure documentParTypeProcedure;
    private Marche marche;

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDelaiTransmission() {
        return delaiTransmission;
    }

    public void setDelaiTransmission(Date delaiTransmission) {
        this.delaiTransmission = delaiTransmission;
    }

    public String getStatut() {
        return statut;
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
    
}
