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
public class ProcedureParTypePrestation {
    private TypePrestation typePrestation;
    private TypeProcedure typeProcedure;

    public ProcedureParTypePrestation(TypePrestation typePrestation, TypeProcedure typeProcedure) {
        this.typePrestation = typePrestation;
        this.typeProcedure = typeProcedure;
    }

    public TypePrestation getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(TypePrestation typePrestation) {
        this.typePrestation = typePrestation;
    }

    public TypeProcedure getTypeProcedure() {
        return typeProcedure;
    }

    public void setTypeProcedure(TypeProcedure typeProcedure) {
        this.typeProcedure = typeProcedure;
    }
    
    
}
