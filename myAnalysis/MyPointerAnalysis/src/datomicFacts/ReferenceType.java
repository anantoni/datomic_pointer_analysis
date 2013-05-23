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
public class ReferenceType {
    
    Type typeRef;
    int id;
    
    public ReferenceType( Type t, int id ) {
        typeRef = t;
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public Type getType() {
        return typeRef;
    }
}
