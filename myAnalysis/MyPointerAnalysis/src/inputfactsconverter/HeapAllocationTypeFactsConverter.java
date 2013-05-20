/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 * anantoni
 */
public class HeapAllocationTypeFactsConverter extends FactsConverter {
    HashMap HeapAllocationRefFactsMap = null;
    HashMap TypeFactsMap = null;
    HashMap HeapAllocationTypeFactsMap = null;
    
    public HeapAllocationTypeFactsConverter() {
        super();
        HeapAllocationRefFactsMap = new HashMap();
        TypeFactsMap = new HashMap();
        HeapAllocationTypeFactsMap = new HashMap();
    }    
    
    @Override
    public int parseLogicBloxFactsFile( int id ) {
        
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/HeapAllocation-Type.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    String pattern = "(.*?)(\\s+)(.+)";
                    // Create a Pattern object
                    Pattern r = Pattern.compile(pattern);

                    // Now create matcher object.
                    Matcher m = r.matcher(line);
                    if ( m.find() ) {
                        if ( m.groupCount() != 3 ) {
                            System.out.println( "Invalid number of groups matched" );
                            System.exit(-1);
                        }
                    } 
                    else {
                        System.out.println( "Could not find match" );
                        System.exit(-1);
                    }
                    
                    HeapAllocationRef har = new HeapAllocationRef( m.group(1) ); //Initialize new InstructionRef object
                    int id1 = id;
                    HeapAllocationRefFactsMap.put( har, id1);                    //Map it to its id
                    
                    
                    Type t = new Type( m.group(3) ); //Initialize new InstructionRef object
                    int id2 = --id;
                    TypeFactsMap.put( t, id2);                    //Map it to its id
                    /** Initialize new InstructionIndex object with the InstructionRef object's id as reference **/
                    HeapAllocationType ii = new HeapAllocationType( har, id1, t, id2 ); 
                                                                                                                    
                    HeapAllocationTypeFactsMap.put( ii, --id );           //decrement id and map the InstructionIndex object to it
                    id--;                                               //decrement id for next cycle
                }
            }
            
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
            
        }
        
        return id;
    }
    
    @Override
    public void createDatomicFactsFile() {
        
    }
}
