/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datomicFacts;

/**
 *
 * @author anantoni
 */
public class InstructionIndex {
    String instructionRef = null;
    int index = -1;
    int instructionRefID = 0;
    
    public InstructionIndex( String ir, int i, int id ) {
        instructionRef = ir;
        index = i;
        instructionRefID = id;
    }
    
    public String getInstructionRef() {
        return instructionRef;
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getInstructionID() {
        return instructionRefID;
    }
}
