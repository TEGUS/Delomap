/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Aurelien KOUAM
 */
public class Marche {
    private int id;
    private String nom;
    private int montant;
    private Date dateDebut;
    private Date dateAttribution;
    private Date dateSignature;
    private Date dateDemarrage;
    private Date dateFin;
    private String autoriteContractante;
    
    private Administration administration;
    private List<Procedure> procedures;
    private List<Document> documents;
    
    private String codeTypePrestation;

    public Marche() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Administration getAdministration() {
        return administration;
    }

    public void setAdministration(Administration administration) {
        this.administration = administration;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getCodeTypePrestation() {
        return codeTypePrestation;
    }

    public void setCodeTypePrestation(String codeTypePrestation) {
        this.codeTypePrestation = codeTypePrestation;
    }

    public Date getDateAttribution() {
        return dateAttribution;
    }

    public void setDateAttribution(Date dateAttribution) {
        this.dateAttribution = dateAttribution;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }

    public Date getDateDemarrage() {
        return dateDemarrage;
    }

    public void setDateDemarrage(Date dateDemarrage) {
        this.dateDemarrage = dateDemarrage;
    }

    public String getAutoriteContractante() {
        return autoriteContractante;
    }

    public void setAutoriteContractante(String autoriteContractante) {
        this.autoriteContractante = autoriteContractante;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    
}

