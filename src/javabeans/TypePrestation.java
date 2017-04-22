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
public class TypePrestation {
    private String code;
    private String nom;
    private List<ProcedureParTypePrestation> procedureParTypePrestations;
    private List<Prestation> prestations;

    public TypePrestation() {
    }

    public TypePrestation(String code) {
        this.code = code;
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

    public List<ProcedureParTypePrestation> getProcedureParTypePrestations() {
        return procedureParTypePrestations;
    }

    public void setProcedureParTypePrestations(List<ProcedureParTypePrestation> procedureParTypePrestations) {
        this.procedureParTypePrestations = procedureParTypePrestations;
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public void setPrestations(List<Prestation> prestations) {
        this.prestations = prestations;
    }

    @Override
    public String toString() {
        return code;
    }
    
    
}
