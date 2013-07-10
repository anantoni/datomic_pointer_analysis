/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datomicFacts;

/**
 *
 * @author
 * anantoni
 */
public class MethodSignatureRef {
    private String methodSignatureRef = null;
    private int id;
    
    public MethodSignatureRef( int id, String methodSignatureRef  ) {
        this.id = id;
        this.methodSignatureRef = methodSignatureRef;
    }
    
    public String getMethod() {
        return methodSignatureRef;
    }
    
    public int getID() {
        return id;
    }
}
