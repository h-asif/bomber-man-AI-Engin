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
public class ArrayList2D extends ArrayList<ArrayList<Integer>>{
    
    //private ArrayList<ArrayList<Integer>> array;
    private int row;
    private int col;
    
    //constructor
    public void ArrayList2D(int row, int col){
        
        this.row = row;
        this.col = col;
        //array = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i < row ; i++){
            ArrayList<Integer> row_array = new ArrayList<Integer>();
            for(int j=0; j< col; j++){
               row_array.add(0);
            }
            this.add(row_array);
        }
    }
    
    public void set(int row, int col, int num){
        
        if( row > this.row ||col > this.col || row < 0 || col < 0)
            return ;
        this.get(row).set(col, new Integer(num) );
    }
    
    public int get(int row, int col){
        
        if( row > this.row ||col > this.col || row < 0 || col < 0)
            return -1;
        return this.get(row).get(col).intValue();
    }
}


