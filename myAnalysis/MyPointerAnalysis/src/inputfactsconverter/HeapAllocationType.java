/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

/**
 *
 * @author
 * anantoni
 */
public class HeapAllocationType {
    HeapAllocationRef heapAllocationRef = null;
    Type type = null;
    int heapAllocationRefID = 0;
    int typeID = 0;    
    
    public HeapAllocationType( HeapAllocationRef har, int har_id, Type t, int t_id ) {
        heapAllocationRef = har;
        type = t;
        heapAllocationRefID = har_id;
        typeID = t_id;
    }
}
