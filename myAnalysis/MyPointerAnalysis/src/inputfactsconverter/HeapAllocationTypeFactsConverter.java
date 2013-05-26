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
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
                    HeapAllocationRef har = new HeapAllocationRef( m.group(1), id-- ); //Initialize new HeapAllocationRef object
                    heapAllocationRefFactsList.add(har);                    //Map it to its id
                    
                    /******************************** Type **********************************************/
                    for ( Type type : typeFactsList ) {
                        if ( m.group(3).equals( type.getType() ) ) {
                            /*************************** HeapAllocationTYpe *****************************/
                            HeapAllocationType hat = new HeapAllocationType( id--, har, type ); 
                            heapAllocationTypeFactsList.add( hat );          //decrement id and map the InstructionIndex object to it
                        }
                    }                    
                }
                br.close();
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
            try ( PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../schema_and_facts/seed-data.dtm", true)));) {
                for ( HeapAllocationRef key : heapAllocationRefFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key.getID() + "]" );
                    writer.println( " :HeapAllocationRef/heap_allocation \""+ key.getHeapAllocationRef().replace( "\\", "\\\\").replace( "\"", "\\\"") +"\"}"); 
                }
                
                for ( HeapAllocationType key2: heapAllocationTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key2.getID() + "]" );
                    writer.println( " :HeapAllocation-Type/heap_allocation #db/id[:db.part/user " + key2.getHeapAllocationRef().getID() + "]");
                    writer.println( " :HeapAllocation-Type/type #db/id[:db.part/user " + key2.getType().getID() + "]}"); 
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
