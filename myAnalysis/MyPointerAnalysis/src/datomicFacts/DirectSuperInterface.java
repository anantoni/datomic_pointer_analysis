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
public class DirectSuperInterface {
    int id;
    Type refClass;
    Type superInterface;
    
    public DirectSuperInterface( int id, Type refClass, Type superInterface ) {
        this.id = id;
        this.refClass = refClass;
        this.superInterface = superInterface;
    }
    
    public int getID() {
        return id;
    }
    
    public Type getRefClassType() {
        return refClass;
    }
    
    public Type getSuperClassType() {
        return superInterface;
    }
}
