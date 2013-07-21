/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

/**
 *
 * @author Wizard
 */
public class Position {
    
    private int row;
    private int col;
    
    
    public Position(int _row, int _col){
        this.row = _row;
        this.col = _col;
    }
    
    public int getNumPosition(int totalCol){
        
        return row*totalCol+col;
    }
    
    public static int getNumPosition(int _row, int _col, int totalCol){
        return _row*totalCol+_col;
    }
    
    
    
    
    public int getRow(){
         return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public boolean equals(Position pos){
        
        if( row == pos.getRow() && col == pos.getCol() ){
            return true;
        }else{
            return false;
        }
             
    }
    
    public boolean rowEquals(Position pos){
        
        if( row == pos.getRow() ){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean colEquals(Position pos){
        
        if( col == pos.getCol() ){
            return true;
        }else{
            return false;
        }
    }
    public void setRow(int row)
    {
        this.row = row;
    }
    
    public void setCol(int col)
    {
        this.col = col;
    }
}
