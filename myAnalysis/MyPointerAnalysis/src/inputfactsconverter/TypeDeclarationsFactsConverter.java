/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

import datomicFacts.ApplicationClass;
import java.util.ArrayList;
import datomicFacts.ClassType;
import datomicFacts.DirectSuperClass;
import datomicFacts.DirectSuperInterface;
import datomicFacts.HeapAllocationRef;
import datomicFacts.HeapAllocationType;
import datomicFacts.InstructionIndex;
import datomicFacts.InstructionRef;
import datomicFacts.MainClass;
import datomicFacts.Type;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 * anantoni
 */
public class TypeDeclarationsFactsConverter extends FactsConverter {
    ArrayList<Type> typeFactsList = null;
    ArrayList<ClassType> classTypeFactsList = null;
    ArrayList<DirectSuperClass> directSuperClassFactsList = null;
    ArrayList<DirectSuperInterface> directSuperInterfaceFactsList = null;
    ArrayList<ApplicationClass> applicationClassFactsList = null;
    ArrayList<MainClass> mainClassFactsList = null;
    
    public TypeDeclarationsFactsConverter() {
        directSuperClassFactsList = new ArrayList<>();
        directSuperInterfaceFactsList = new ArrayList<>();
        applicationClassFactsList = new ArrayList<>();
        mainClassFactsList = new ArrayList<>();
    }
    
    @Override
    public int parseLogicBloxFactsFile(int id) {
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/DirectSuperclass.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    String pattern = "(.*?)(\\s+)(.*+)";
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
                    Type refClass = null;
                    Type superClass = null;
                    
                    for ( Type type : typeFactsList ) {
                        if ( type.getType().equals( m.group(1) ) ) {
                            refClass = type;
                            break;
                        }
                    }
                    for ( Type type : typeFactsList ) {
                        if ( type.getType().equals( m.group(3) ) ) {
                            superClass = type;
                            break;
                        }
                    }
                    DirectSuperClass directSuperClass = new DirectSuperClass( id--, refClass, superClass );
                    directSuperClassFactsList.add( directSuperClass );
                    
                }
                br.close();
            }
            
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/DirectSuperinterface.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    String pattern = "(.*?)(\\s+)(.*+)";
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
                    Type refClass = null;
                    Type superInterface = null;
                    
                    for ( Type type : typeFactsList ) {
                        if ( type.getType().equals( m.group(1) ) ) {
                            refClass = type;
                            break;
                        }
                    }
                    for ( Type type : typeFactsList ) {
                        if ( type.getType().equals( m.group(3) ) ) {
                            superInterface = type;
                            break;
                        }
                    }
                    DirectSuperInterface directSuperInterface = new DirectSuperInterface( id--, refClass, superInterface );
                    directSuperInterfaceFactsList.add( directSuperInterface );
                    
                }
                br.close();
            }
            
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/ApplicationClass.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    Type refClass = null;
                    
                    for ( Type type : typeFactsList ) {
                        if ( type.getType().equals( line ) ) {
                            refClass = type;
                            break;
                        }
                    }
                    
                    ApplicationClass applicationClass = new ApplicationClass( id--, refClass );
                    applicationClassFactsList.add(applicationClass);
                                      
                }
                br.close();
            }
     
        }
        catch( IOException ex) {
            System.out.println( ex.toString() );
            System.exit(-1);
            
        }
        
        return id;
    }
    
    @Override
    public void createDatomicFactsFile() {
        try {
            try ( PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../schema_and_facts/seed-data.dtm", true)));) {
                for ( DirectSuperClass key : directSuperClassFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key.getID() + "]" );
                    writer.println( " :DirectSuperclass/ref #db/id[:db.part/user " + key.getRefClassType().getID() +"]"); 
                    writer.println( " :DirectSuperclass/super #db/id[:db.part/user " + key.getSuperClassType().getID() + "]}");
                }
                
                for ( DirectSuperInterface key2: directSuperInterfaceFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key2.getID() + "]" );
                    writer.println( " :DirectSuperinterface/class #db/id[:db.part/user " + key2.getClassType().getID() +"]"); 
                    writer.println( " :DirectSuperinterface/interace #db/id[:db.part/user " + key2.getInterfaceType().getID() + "]}");
                }
                
                for ( ApplicationClass key3 : applicationClassFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key3.getID() + "]" );
                    writer.println( " :ApplicationClass/ref #db/id[:db.part/user " + key3.getType().getID() + "]}");


                }
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
    
    public void setClassTypeFactsList( ArrayList<ClassType> classTypeFactsList ) {
        this.classTypeFactsList = classTypeFactsList;
    }
    
}
