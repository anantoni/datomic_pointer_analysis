/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datomicFacts;

/**
 *
 * @author
 * anantoni
 */
public class InterfaceType {
    ReferenceType refTypeRef;
    int id;
    
    public InterfaceType( ReferenceType t, int id ) {
        refTypeRef = t;
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public ReferenceType getType() {
        return refTypeRef;
    }
}
