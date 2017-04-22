/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

/**
 *
 * @author Aurelien KOUAM
 */
public class DocumentParTypeProcedure {
    private TypeProcedure typeProcedure;
    private TypeDocument typeDocument;

    public DocumentParTypeProcedure(TypeProcedure typeProcedure, TypeDocument typeDocument) {
        this.typeProcedure = typeProcedure;
        this.typeDocument = typeDocument;
    }

    public TypeProcedure getTypeProcedure() {
        return typeProcedure;
    }

    public void setTypeProcedure(TypeProcedure typeProcedure) {
        this.typeProcedure = typeProcedure;
    }

    public TypeDocument getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(TypeDocument typeDocument) {
        this.typeDocument = typeDocument;
    }
    
    
}
