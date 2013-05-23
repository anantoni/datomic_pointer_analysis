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
public class NullType {
    Type typeRef;
    int id;
    
    public NullType( Type t, int id ) {
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
