/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

/**
 *
 * @author anantoni
 */
public class InputFactsConverter {

    /**
     * @param args the command line arguments
     */
    public InputFactsConverter() {
        Integer id = -1000;
        /************* Instruction-Index facts converter *********************/
        InstructionIndexFactsConverter ifc = new InstructionIndexFactsConverter();
        id = ifc.parseLogicBloxFactsFile(id);
        ifc.createDatomicFactsFile();
        System.out.println( id );
        
        /************* HeapAllocation-Type facts converter *********************/
        HeapAllocationTypeFactsConverter hatfc = new HeapAllocationTypeFactsConverter();
        id = hatfc.parseLogicBloxFactsFile(id);
        hatfc.createDatomicFactsFile();
        System.out.println( id );
    }
}
