/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;
import datomicFacts.Type;
import java.util.ArrayList;

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

        
        /************* Instruction-Index facts converter *********************/
        TypeFactsConverter tfc = new TypeFactsConverter();
        id = tfc.parseLogicBloxFactsFile(id);
        tfc.createDatomicFactsFile();
        System.out.println( id );
        
        /************* HeapAllocation-Type facts converter *********************/
        HeapAllocationTypeFactsConverter hatfc = new HeapAllocationTypeFactsConverter();
        hatfc.setTypeFactsList( tfc.getTypeFactsList() );
        id = hatfc.parseLogicBloxFactsFile(id);
        hatfc.createDatomicFactsFile();
        System.out.println( id );
        
        TypeDeclarationsFactsConverter tdfc = new TypeDeclarationsFactsConverter();
        tdfc.setTypeFactsList(tfc.getTypeFactsList());
        tdfc.setClassTypeFactsList(tfc.getClassTypeFactsList());
        id = tdfc.parseLogicBloxFactsFile(id);
        tdfc.createDatomicFactsFile();
        System.out.println( id );
        
        VariableDeclarationsFactsConverter vdfc = new VariableDeclarationsFactsConverter();
        vdfc.setTypeFactsList(tfc.getTypeFactsList());
        System.out.println( "Nini" );
        id = vdfc.parseLogicBloxFactsFile(id);
        System.out.println("Ninia");
        vdfc.createDatomicFactsFile();
        System.out.println("Niniania");
        System.out.println( id );
        
    }
}
