/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

import datomicFacts.MethodSignatureRef;
import datomicFacts.Type;
import datomicFacts.VarDeclaringMethod;
import datomicFacts.VarRef;
import datomicFacts.VarType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 * anantoni
 */
public class VariableDeclarationsFactsConverter extends FactsConverter {
    ArrayList<Type> typeFactsList = null;
    ArrayList<VarRef> varRefFactsList = null;
    ArrayList<MethodSignatureRef> methodSignatureRefFactsList = null;
    ArrayList<VarType> varTypeFactsList = null;
    ArrayList<VarDeclaringMethod> varDeclaringMethodFactsList = null;
    
    
    public VariableDeclarationsFactsConverter() {
        typeFactsList = new ArrayList<>();
        varRefFactsList = new ArrayList<>();
        varTypeFactsList = new ArrayList<>();
        methodSignatureRefFactsList = new ArrayList<>();
        varDeclaringMethodFactsList = new ArrayList<>();
    }
    
    @Override
    public int parseLogicBloxFactsFile(int id) {
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/VarRef.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    
                    VarRef varRef = new VarRef( id--, line );
                    varRefFactsList.add( varRef );
                    
                }
                br.close();
            }
            
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/MethodSignatureRef.facts" ) )) {
                String line;
                while ((line = br.readLine()) != null) {
                    
                    MethodSignatureRef methodSignatureRef = new MethodSignatureRef( id--, line );
                    methodSignatureRefFactsList.add( methodSignatureRef );
                    
                }
                br.close();
            }
            
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/Var-Type.facts" ) )) {
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
                    VarRef ref = null;
                    Type typeRef = null;
                    
                    
                    for ( VarRef varRef : varRefFactsList ) {
                        if ( varRef.getValue().equals( m.group(1) ) ) {
                            ref = varRef;
                            break;
                        }
                    }
                    for ( Type type : typeFactsList ) {
                        if ( type.getType().equals( m.group(3) ) ) {
                            typeRef = type;
                            break;
                        }
                    }
                    VarType varType = new VarType( id--, ref, typeRef );
                    varTypeFactsList.add( varType );
                    
                }
                br.close();
            }
            
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/Var-DeclaringMethod.facts" ) )) {
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
                    VarRef ref = null;
                    MethodSignatureRef method = null;
                    
                    
                    for ( VarRef varRef : varRefFactsList ) {
                        if ( varRef.getValue().equals( m.group(1) ) ) {
                            ref = varRef;
                            break;
                        }
                    }
                    
                    for ( MethodSignatureRef methodSignatureRef : methodSignatureRefFactsList ) {
                        if ( methodSignatureRef.getMethod().equals( m.group(3) ) ) {
                            method = methodSignatureRef;
                            break;
                        }
                    }
                    VarDeclaringMethod varDeclaringMethod= new VarDeclaringMethod( id--, ref, method );
                    varDeclaringMethodFactsList.add( varDeclaringMethod );
                    
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
                for ( VarRef key : varRefFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key.getID() + "]" );
                    writer.println( " :VarRef/value #db/id[:db.part/user " + key.getValue() + "]}");
                }
                
                for ( MethodSignatureRef key : methodSignatureRefFactsList) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key.getID() + "]" );
                    writer.println( " :VarRef/value #db/id[:db.part/user " + key.getMethod() + "]}");
                }
                
                for ( VarType key : varTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key.getID() + "]" );
                    writer.println( " :Var-Type/ref #db/id[:db.part/user " + key.getVarRef().getID() +"]"); 
                    writer.println( " :Var-Type/type #db/id[:db.part/user " + key.getType().getID() + "]}");
                }
                System.out.println( "Var-Type facts converted" );
                for ( VarDeclaringMethod key : varDeclaringMethodFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + key.getID() + "]" );
                    writer.println( " :Var-DeclaringMethod/ref #db/id[:db.part/user " + key.getMethodSignatureRef().getID() +"]"); 
                    writer.println( " :Var-DeclaringMethod/method #db/id[:db.part/user " + key.getMethodSignatureRef().getID() + "]}");                
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
