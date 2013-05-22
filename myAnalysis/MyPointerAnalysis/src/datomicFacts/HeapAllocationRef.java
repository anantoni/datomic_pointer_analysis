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
    
    public HeapAllocationRef( String har ) {
        heapAllocationRef = har;
    }
    
    public String getHeapAllocationRef() {
        return heapAllocationRef;
    }
    
}
