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
public class TypeDocument {
    private String code;
    private String nom;
    private String phase;
    private List<DocumentParTypeProcedure> documentParTypeProcedures;

    public TypeDocument() {
    }

    public TypeDocument(String code, String nom, String phase) {
        this.code = code;
        this.nom = nom;
        this.phase = phase;
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public List<DocumentParTypeProcedure> getDocumentParTypeProcedures() {
        return documentParTypeProcedures;
    }

    public void setDocumentParTypeProcedures(List<DocumentParTypeProcedure> documentParTypeProcedures) {
        this.documentParTypeProcedures = documentParTypeProcedures;
    }
}
