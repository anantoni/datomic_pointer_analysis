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
public class HeapAllocationRef {
    private String heapAllocationRef = null;
    private int id;
    
    public HeapAllocationRef( String har, int id ) {
        heapAllocationRef = har;
        this.id = id;
    }
    
    public String getHeapAllocationRef() {
        return heapAllocationRef;
    }
    
    public int getID() {
        return id;
    }
    
}
