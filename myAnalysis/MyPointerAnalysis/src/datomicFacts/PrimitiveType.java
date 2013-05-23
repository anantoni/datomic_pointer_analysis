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
public class PrimitiveType {
    Type typeRef;
    int id;
    
    public PrimitiveType( Type t, int id ) {
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
