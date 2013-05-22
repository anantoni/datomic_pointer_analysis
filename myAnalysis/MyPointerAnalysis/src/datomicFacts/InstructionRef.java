/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datomicFacts;

/**
 *
 * @author anantoni
 */
public class InstructionRef {
    private String instructionRef = null;
    
    public InstructionRef( String ir ) {
        instructionRef = ir;
    }
    
    public String getInstruction() {
        return instructionRef;
    }
}
