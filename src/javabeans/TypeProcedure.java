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
public class TypeProcedure {
    private String code;
    private String nom;
    private List<DocumentParTypeProcedure> documentParTypeProcedures;
    private List<ProcedureParTypePrestation> procedureParTypePrestations;
    private List<Procedure> procedures;

    public TypeProcedure() {
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

    public List<DocumentParTypeProcedure> getDocumentParTypeProcedures() {
        return documentParTypeProcedures;
    }

    public void setDocumentParTypeProcedures(List<DocumentParTypeProcedure> documentParTypeProcedures) {
        this.documentParTypeProcedures = documentParTypeProcedures;
    }

    public List<ProcedureParTypePrestation> getProcedureParTypePrestations() {
        return procedureParTypePrestations;
    }

    public void setProcedureParTypePrestations(List<ProcedureParTypePrestation> procedureParTypePrestations) {
        this.procedureParTypePrestations = procedureParTypePrestations;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
    
    
}
