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
public class ArrayType {

    ReferenceType refTypeRef;
    int id;
    
    public ArrayType( ReferenceType t, int id ) {
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
