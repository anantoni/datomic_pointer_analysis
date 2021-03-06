/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

import datomicFacts.ArrayType;
import datomicFacts.ClassType;
import datomicFacts.HeapAllocationRef;
import datomicFacts.Type;
import datomicFacts.HeapAllocationType;
import datomicFacts.InterfaceType;
import datomicFacts.NullType;
import datomicFacts.PrimitiveType;
import datomicFacts.ReferenceType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author
 * anantoni
 */
public class TypeFactsConverter extends FactsConverter {
    private ArrayList<Type> typeFactsList;
    private ArrayList<ArrayType> arrayTypeFactsList;
    private ArrayList<ClassType> classTypeFactsList;
    //private ArrayList<ComponentType> componentTypeFactsList;
    private ArrayList<InterfaceType> interfaceTypeFactsList;
    private ArrayList<NullType> nullTypeFactsList;
    private ArrayList<PrimitiveType> primitiveTypeFactsList;
    private ArrayList<ReferenceType> referenceTypeFactsList;
    
    
    public TypeFactsConverter() {
        super();
        typeFactsList = new ArrayList();
        arrayTypeFactsList = new ArrayList();
        classTypeFactsList = new ArrayList();
        //componentTypeFactsList = new ArrayList();
        interfaceTypeFactsList = new ArrayList();
        nullTypeFactsList = new ArrayList();
        primitiveTypeFactsList = new ArrayList();
        referenceTypeFactsList = new ArrayList();                
    }    
    
    @Override
    public int parseLogicBloxFactsFile( int id ) {
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/ArrayType.facts" ) )) {
                String line;

                while ((line = br.readLine()) != null) {
                    boolean typeFound = false;
                    for ( ArrayType item : arrayTypeFactsList ) {
                        if ( line.equals( item.getType().getType().getType() ) ) {
                                typeFound = true;
                                break;
                        }
                    }
                    if (typeFound)
                        continue;
                    else {
                        Type type = new Type( line, id-- );
                        ReferenceType refType = new ReferenceType( type, id-- );
                        ArrayType arrayType = new ArrayType( refType, id-- );
                        typeFactsList.add(type);
                        referenceTypeFactsList.add(refType);
                        arrayTypeFactsList.add(arrayType);
                    }
                }
                br.close();
            }
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
        }
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/ClassType.facts" ) )) {
                String line;

                while ((line = br.readLine()) != null) {
                    boolean typeFound = false;
                    for ( ClassType item : classTypeFactsList ) {
                        if ( line.equals( item.getType().getType().getType() ) ) {
                                typeFound = true;
                                break;
                        }
                    }
                    if (typeFound)
                        continue;
                    else {
                        Type type = new Type( line, id-- );
                        ReferenceType refType = new ReferenceType( type, id-- );
                        ClassType classType = new ClassType( refType, id-- );
                        typeFactsList.add(type);
                        referenceTypeFactsList.add(refType);
                        classTypeFactsList.add(classType);
                    }
                }
                br.close();
            }
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
        }
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/InterfaceType.facts" ) )) {
                String line;

                while ((line = br.readLine()) != null) {
                    boolean typeFound = false;
                    for ( InterfaceType item : interfaceTypeFactsList ) {
                        if ( line.equals( item.getType().getType().getType() ) ) {
                                typeFound = true;
                                break;
                        }
                    }
                    if (typeFound)
                        continue;
                    else {
                        Type type = new Type( line, id-- );
                        ReferenceType refType = new ReferenceType( type, id-- );
                        InterfaceType interfaceType = new InterfaceType( refType, id-- );
                        typeFactsList.add(type);
                        referenceTypeFactsList.add(refType);
                        interfaceTypeFactsList.add(interfaceType);
                    }
                }
                br.close();
            }
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
        }
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/NullType.facts" ) )) {
                String line;

                while ((line = br.readLine()) != null) {
                    boolean typeFound = false;
                    for ( InterfaceType item : interfaceTypeFactsList ) {
                        if ( line.equals( item.getType().getType().getType() ) ) {
                                typeFound = true;
                                break;
                        }
                    }
                    if (typeFound)
                        continue;
                    else {
                        Type type = new Type( line, id-- );
                        NullType nullType = new NullType( type, id-- );
                        typeFactsList.add(type);
                        nullTypeFactsList.add(nullType);
                    }
                }
                br.close();
            }
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
        }
        
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/PrimitiveType.facts" ) )) {
                String line;

                while ((line = br.readLine()) != null) {
                    boolean typeFound = false;
                    for ( PrimitiveType item : primitiveTypeFactsList ) {
                        if ( line.equals( item.getType().getType() ) ) {
                                typeFound = true;
                                break;
                        }
                    }
                    if (typeFound)
                        continue;
                    else {
                        Type type = new Type( line, id-- );
                        PrimitiveType primitiveType = new PrimitiveType( type, id-- );
                        typeFactsList.add(type);
                        primitiveTypeFactsList.add(primitiveType);
                        
                    }
                }
                br.close();
            }
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
        }
        
//        try {
//            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/ReferenceType.facts" ) )) {
//                String line;
//
//                while ((line = br.readLine()) != null) {
//                    boolean typeFound = false;
//                    for ( ReferenceType item : referenceTypeFactsList ) {
//                        if ( line.equals( item.getType().getType() ) ) {
//                                typeFound = true;
//                                break;
//                        }
//                    }
//                    if (typeFound)
//                        continue;
//                    else {
//                        Type type = new Type( line, id-- );
//                        ReferenceType refType = new ReferenceType( type, id-- );
//                        typeFactsList.add(type);
//                        referenceTypeFactsList.add(refType);
//                    }
//                }
//            }
//        }
//        catch( Exception ex) {
//            System.out.println( ex.toString() ); 
//        }
        /*
        try {
            try (BufferedReader br = new BufferedReader( new FileReader( "../cache/input-facts/ComponentType.facts" ) )) {
                String line;
                int refID;
                List<ComponentType> componentTypekeys = new ArrayList<>( componentTypeFactsMap.keySet() );
                while ((line = br.readLine()) != null) {
                    boolean keyFound = false;
                    for ( ComponentType key : componentTypekeys ) {
                        if ( line.equals( key.getType() ) ) {
                                keyFound = true;
                                break;
                        }
                    }
                    if (keyFound)
                        continue;
                    else {
                        refID = id;
                        typeFactsMap.put( new Type(line), id-- );
                        componentTypeFactsMap.put( new ComponentType(line, refID), id--);
                        
                    }
                        
                }
            }
            
        }
        catch( Exception ex) {
            System.out.println( ex.toString() ); 
            
        }*/
        return id;
    }
    
    @Override
    public void createDatomicFactsFile() {
        try {
            /*************************** Type.dtm **********************************************/
            try ( PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../schema_and_facts/seed-data.dtm", true)));) {
                
                for ( Type type: typeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + type.getID() + "]" );
                    writer.println( " :Type/type \""+ type.getType() +"\"}"); 
                } 
                
            
            /********************************* ArrayType.dtm ***********************************/
               
                for ( ArrayType arrayType: arrayTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + arrayType.getID() + "]" );
                    writer.println( " :ArrayType/reference_type #db/id[:db.part/user " + arrayType.getType().getID() + "]}");  
                } 
            /********************************* ClassType.dtm ***********************************/
               
                for ( ClassType classType: classTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + classType.getID() + "]" );
                    writer.println( " :ClassType/reference_type #db/id[:db.part/user " + classType.getType().getID() + "]}");  
                } 
                
            /********************************* InterfaceType.dtm ***********************************/
               
                for ( InterfaceType interfaceType: interfaceTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + interfaceType.getID() + "]" );
                    writer.println( " :InterfaceType/reference_type #db/id[:db.part/user " + interfaceType.getType().getID() + "]}");  
                } 
               
            
            /********************************* PrimitiveType.dtm ***********************************/
               
                for ( PrimitiveType primitiveType: primitiveTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + primitiveType.getID() + "]" );
                    writer.println( " :PrimitiveType/type #db/id[:db.part/user " + primitiveType.getType().getID() + "]}");  
                } 
               
                
            
            /********************************* ReferenceType.dtm ***********************************/
               
                for ( ReferenceType referenceType: referenceTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + referenceType.getID() + "]" );
                    writer.println( " :ReferenceType/type #db/id[:db.part/user " + referenceType.getType().getID() + "]}");  
                } 
                
            
            /********************************* NullType.dtm ***********************************/
               
                for ( NullType nullType : nullTypeFactsList ) {
                    writer.println( "{:db/id #db/id[:db.part/user " + nullType.getID() + "]" );
                    writer.println( " :NullType/type #db/id[:db.part/user " + nullType.getType().getID() + "]}");  
                } 
                writer.close();
                
            }        
        }
        catch ( Exception ex ) {
            System.out.println( ex.toString() ); 
            System.exit(-1);
        }
    }
    
    public ArrayList<Type> getTypeFactsList() {
        return typeFactsList;
    }
    
    public ArrayList<ClassType> getClassTypeFactsList() {
        return classTypeFactsList;
    }
}
