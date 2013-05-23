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
public class Type {
    private String type = null;
    private int refID;
    
    public Type( String t, int id ) {
        type = t;
        refID = id;
    }
    
    public String getType() {
        return type;
    }
    
    public int getID() {
        return refID;
    }
}
