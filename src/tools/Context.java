/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Aurelien KOUAM
 */
public class Context {
    private final static Context instance = new Context();
    
    public static Context getInstance() {
        return instance;
    }
    
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
