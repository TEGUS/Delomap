/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.factory;

import javabeans.Administration;
import javabeans.Document;
import javabeans.Marche;
import javabeans.Prestation;
import javabeans.Procedure;
import javabeans.TypeDocument;
import javabeans.TypePrestation;
import javabeans.TypeProcedure;
import pattern.dao.AdministrationDAO;
import pattern.dao.DAO;
import pattern.dao.DocumentDAO;
import pattern.dao.MarcheDAO;
import pattern.dao.PrestationDAO;
import pattern.dao.ProcedureDAO;
import pattern.dao.TypeDocumentDAO;
import pattern.dao.TypePrestationDAO;
import pattern.dao.TypeProcedureDAO;

/**
 *
 * @author Aurelien KOUAM
 */
public class DAOFactory {
    
    public static DAO<Administration> getAdministrationDAO() {
        return new AdministrationDAO();
    }
    
    public static DAO<Document> getDocumentDAO() {
        return new DocumentDAO();
    }
    
    public static DAO<Marche> getMarcheDAO() {
        return new MarcheDAO();
    }
    
    public static DAO<Prestation> getPrestationDAO() {
        return new PrestationDAO();
    }
    
    public static DAO<Procedure> getProcedureDAO() {
        return new ProcedureDAO();
    }
    
    public static DAO<TypeProcedure> getTypeProcedureDAO() {
        return new TypeProcedureDAO();
    }
    
    public static DAO<TypePrestation> getTypePrestationDAO() {
        return new TypePrestationDAO();
    }
    
    public static DAO<TypeDocument> getTypeDocumentDAO() {
        return new TypeDocumentDAO();
    }
}
