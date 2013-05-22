/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

import datomicFacts.HeapAllocationRef;
import datomicFacts.Type;
import datomicFacts.HeapAllocationType;
import datomicFacts.InstructionIndex;
import datomicFacts.InstructionRef;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 * anantoni
 */
public class HeapAllocationTypeFactsConverter extends FactsConverter {
    HashMap heapAllocationRefFactsMap = null;
    HashMap typeFactsMap = null;
    HashMap heapAllocationTypeFactsMap = null;
    
    public HeapAllocationTypeFactsConverter() {
        super();
        heapAllocationRefFactsMap = new HashMap();
        typeFactsMap = new HashMap();
        heapAllocationTypeFactsMap = new HashMap();
    }    
    
    @Override
    public int parseLogicBloxFactsFile( int id ) {
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/HeapAllocation-Type.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    String pattern = "(.*)(\\s+)(.+)";
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
                    
                    /**************************** HeapAllocationRef **********************************/
                    HeapAllocationRef har = new HeapAllocationRef( m.group(1) ); //Initialize new HeapAllocationRef object
                    int id1 = id;
                    heapAllocationRefFactsMap.put( har, id1);                    //Map it to its id
                    
                    /******************************** Type **********************************************/
                    Type t = new Type( m.group(3) );        //Initialize new InstructionRef object
                    int id2 = --id;
                    typeFactsMap.put( t, id2);              //Map it to its id
                    
                    /*************************** HeapAllocationTYpe *****************************/
                    HeapAllocationType ii = new HeapAllocationType( har, id1, t, id2 ); 
                    heapAllocationTypeFactsMap.put( ii, --id );           //decrement id and map the InstructionIndex object to it
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
        try {
            try (PrintWriter writer = new PrintWriter("../schema_and_facts/HeapAllocation-Type.dtm", "UTF-8")) {
                List<HeapAllocationRef> heapAllocationRefKeys = new ArrayList<>( heapAllocationRefFactsMap.keySet() );
                writer.println("[");
                for ( HeapAllocationRef key: heapAllocationRefKeys ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + heapAllocationRefFactsMap.get(key) + "]" );
                    writer.println( " :HeapAllocationRef/heap_allocation \""+ key.getHeapAllocationRef().replace( "\\", "\\\\").replace( "\"", "\\\"") +"\"}"); 
                } 
                
                List<Type> typeKeys = new ArrayList<>( typeFactsMap.keySet() );
                for ( Type key1: typeKeys ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + typeFactsMap.get(key1) + "]" );
                    writer.println( " :Type/type \"" + key1.getType() + "\"}" );
                }
                List<HeapAllocationType> heapAllocationTypeKeys = new ArrayList<>( heapAllocationTypeFactsMap.keySet() );
                for ( HeapAllocationType key2: heapAllocationTypeKeys ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + heapAllocationTypeFactsMap.get(key2) + "]" );
                    writer.println( " :HeapAllocation-Type/heap_allocation #db/id[:db.part/user " + key2.getHeapAllocationRefID() + "]");
                    writer.println( " :HeapAllocation-Type/type #db/id[:db.part/user " + key2.getTypeID() + "]}"); 
                }
                writer.println("]");
                writer.close();
            }
            
        }
        catch ( Exception ex ) {
            System.out.println( ex.toString() ); 
            System.exit(-1);
        }
        
    }
}
