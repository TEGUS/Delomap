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
public class Administration {
    private String code;
    private String nom;
    private String type;
    private String adresseMail;
    private List<Marche> marches;

    public Administration() {
    }

    public Administration(String code) {
        this.code = code;
    }

    public Administration(String code, String nom, String type, String adresseMail) {
        this.code = code;
        this.nom = nom;
        this.type = type;
        this.adresseMail = adresseMail;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public List<Marche> getMarches() {
        return marches;
    }

    public void setMarches(List<Marche> marches) {
        this.marches = marches;
    }
    
    
}
