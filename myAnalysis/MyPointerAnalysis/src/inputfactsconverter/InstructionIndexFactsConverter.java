/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;
import datomicFacts.InstructionIndex;
import datomicFacts.InstructionRef;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author anantoni
 */
public class InstructionIndexFactsConverter extends FactsConverter {
    private HashMap<InstructionRef, Integer> instructionFactsMap = null;
    private HashMap<InstructionIndex, Integer> instructionIndexFactsMap = null;
    
    public InstructionIndexFactsConverter() {
        super();
        instructionFactsMap = new HashMap();
        instructionIndexFactsMap = new HashMap();
    }
    
    @Override
    public int parseLogicBloxFactsFile( int id ) {        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/Instruction-Index.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    String pattern = "(.*?)(\\s+)(\\d+)";
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
                    
                    InstructionRef ir = new InstructionRef( m.group(1) ); //Initialize new InstructionRef object
                    instructionFactsMap.put( ir, id);                    //Map it to its id

                    /** Initialize new InstructionIndex object with the InstructionRef object's id as reference **/
                    InstructionIndex ii = new InstructionIndex( m.group(1), Integer.parseInt( m.group(3) ), id ); 
                    instructionIndexFactsMap.put( ii, --id );           //decrement id and map the InstructionIndex object to it
                    id--;                                               //decrement id for next cycle
                }
            }
            
        }
        catch( IOException | NumberFormatException ex) {
            System.out.println( ex.toString() );
            System.exit(-1);
            
        }
        return id;
    }
    
    /**
     *
     */
    @Override
    public void createDatomicFactsFile() {
        try {
            try ( PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../schema_and_facts/seed-data.dtm", false))); ) {
                List<InstructionRef> instructionKeys = new ArrayList<>( instructionFactsMap.keySet() );
                writer.println("[");
                for ( InstructionRef key: instructionKeys) {
                    writer.println( "{:db/id #db/id[:db.part/user " + instructionFactsMap.get(key) + "]" );
                    writer.println( " :InstructionRef/instruction \""+ key.getInstruction() +"\"}"); 
                } 
                
                List<InstructionIndex> instructionIndexKeys = new ArrayList<>( instructionIndexFactsMap.keySet() );
                for ( InstructionIndex key1: instructionIndexKeys) {
                    writer.println( "{:db/id #db/id[:db.part/user " + instructionIndexFactsMap.get(key1) + "]" );
                    writer.println( " :Instruction-Index/instruction #db/id[:db.part/user " + key1.getInstructionID() + "]");
                    writer.println( " :Instruction-Index/index " + key1.getIndex() + "}" );
                }
                writer.close();
            }
            
        }
        catch ( Exception ex ) {
            System.out.println( ex.toString() ); 
            System.exit(-1);
        }
    }
    
    public HashMap<InstructionRef, Integer> getInstructionFactsMap() {
        return instructionFactsMap;
    }
    
}