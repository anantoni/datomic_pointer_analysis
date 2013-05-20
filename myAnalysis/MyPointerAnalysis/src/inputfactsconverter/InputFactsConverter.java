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
        InstructionIndexFactsConverter ifc = new InstructionIndexFactsConverter();
        id = ifc.parseLogicBloxFactsFile(id);
        ifc.createDatomicFactsFile();
        System.out.println( id );
        HeapAllocationTypeFactsConverter hatfc = new HeapAllocationTypeFactsConverter();
        id = hatfc.parseLogicBloxFactsFile(id);
        System.out.println( id );
    }
}
