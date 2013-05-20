/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfactsconverter;

/**
 *
 * @author
 * anantoni
 */
public abstract class FactsConverter {
    
    abstract int parseLogicBloxFactsFile(int id);
    
    abstract void createDatomicFactsFile();
    
}
