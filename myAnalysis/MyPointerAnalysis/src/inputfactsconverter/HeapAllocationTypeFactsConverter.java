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
    ArrayList<HeapAllocationRef> heapAllocationRefFactsList = null;
    ArrayList<HeapAllocationType> heapAllocationTypeFactsList = null;
    ArrayList<Type> typeFactsList = null;
    
    public HeapAllocationTypeFactsConverter() {
        super();
        heapAllocationRefFactsList = new ArrayList();
        heapAllocationTypeFactsList = new ArrayList();
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
                    
                    int id1 = id;
                    HeapAllocationRef har = new HeapAllocationRef( m.group(1), id ); //Initialize new HeapAllocationRef object
                    heapAllocationRefFactsList.add(har);                    //Map it to its id
                    
                    /******************************** Type **********************************************/
                    
                    for ( Type type : typeFactsList ) {
                        if ( m.group(3).equals( type.getType() ) ) {
                            int id2 = type.getID();
                            /*************************** HeapAllocationTYpe *****************************/
                            HeapAllocationType hat = new HeapAllocationType( --id, har, id1, type, id2 ); 
                            heapAllocationTypeFactsList.add( hat );          //decrement id and map the InstructionIndex object to it
                            id--;                                               //decrement id for next cycle
                        }
                    }
                    
                    
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
    
    public void setTypeFactsList( ArrayList<Type> typeFactsList ) {
        this.typeFactsList = typeFactsList;
    }
}
