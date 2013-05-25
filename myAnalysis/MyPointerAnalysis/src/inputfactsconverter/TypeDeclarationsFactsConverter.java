/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

import java.util.ArrayList;
import datomicFacts.ClassType;
import datomicFacts.Type;


/**
 *
 * @author
 * anantoni
 */
public class TypeDeclarationsFactsConverter extends FactsConverter {
    ArrayList<Type> typeFactsList = null;
    ArrayList<ClassType> classTypeFactsList = null;
    
    @Override
    public int parseLogicBloxFactsFile(int id) {
        
        return id;
    }
    
    @Override
    public void createDatomicFactsFile() {
        
    }
    
    
    
    public void setTypeFactsList( ArrayList<Type> typeFactsList ) {
        this.typeFactsList = typeFactsList;
        
    }
    
    public void setClassTypeFactsList( ArrayList<ClassType> classTypeFactsList ) {
        this.classTypeFactsList = classTypeFactsList;
    }
    
}
