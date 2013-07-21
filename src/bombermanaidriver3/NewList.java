/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.ArrayList;

/**
 *
 * @author Wizard
 */
public class NewList extends ArrayList<ArrayList<Integer>>{
    
    public void NewList(){   
    }
    
    ////overriden
    public void set(int row, int col, int num){
        this.get(row).set(col, num);
    }
    
    //overriden
    public int get(int row, int col){
        return this.get(row).get(col).intValue();
    }
}
