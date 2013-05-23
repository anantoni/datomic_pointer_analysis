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
public class HeapAllocationType {
    HeapAllocationRef heapAllocationRef = null;
    Type type = null;
    int id = 0;
    int heapAllocationRefID = 0;
    int typeID = 0;    
    
    public HeapAllocationType( int id, HeapAllocationRef har, int har_id, Type t, int t_id ) {
        this.id = id;
        heapAllocationRef = har;
        type = t;
        heapAllocationRefID = har_id;
        typeID = t_id;
    }
    
    public int getHeapAllocationRefID() {
        return heapAllocationRefID;
    }
    
    public int getTypeID() {
        return typeID;
    }
}
