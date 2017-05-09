/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.util.Date;

/**
 *
 * @author Aurelien KOUAM
 */
public class FicheSuivi {
    private int id;
    private String designation;
    private int montant;
    private Date dateReception;
    private Date dateLancement;
    private String motif;
    private String observation;

    public FicheSuivi() {
    }

    public FicheSuivi(int id) {
        this.id = id;
    }

    public FicheSuivi(String designation, int montant, Date dateReception, Date dateLancement, String motif, String observation) {
        this.designation = designation;
        this.montant = montant;
        this.dateReception = dateReception;
        this.dateLancement = dateLancement;
        this.motif = motif;
        this.observation = observation;
    }

    public FicheSuivi(int id, String designation, int montant, Date dateReception, Date dateLancement, String motif, String observation) {
        this.id = id;
        this.designation = designation;
        this.montant = montant;
        this.dateReception = dateReception;
        this.dateLancement = dateLancement;
        this.motif = motif;
        this.observation = observation;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }

    public Date getDateLancement() {
        return dateLancement;
    }

    public void setDateLancement(Date dateLancement) {
        this.dateLancement = dateLancement;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "FicheSuivi{" + "id=" + id + ", designation=" + designation + ", montant=" + montant + ", dateReception=" + dateReception + ", dateLancement=" + dateLancement + ", motif=" + motif + ", observation=" + observation + '}';
    }
}
