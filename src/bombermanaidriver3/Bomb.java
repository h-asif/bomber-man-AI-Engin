/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

/**
 *
 * @author Wizard
 */
public class Bomb {
   
    //instance veriables
    /*
     * range: no of blooks the explosion can cover in one direction
     * explosionTimeSpan: time taken by bomb to explod from the time it is installed
     * flameTimeSpan: the time for which the flame will last on the map
     * player: player who installed the bomb
     */
     
    private int range, explosionTimeSpan, flameTimeSpan, player; 
    private boolean exploded;
    private Position position;
    
    
    //Constructor-1
    public Bomb(int _row, int _col, int _range, int _player){
        range = _range;
        explosionTimeSpan = 3*4;
        exploded = false;
        position = new Position( _row, _col);
        player = _player;
        flameTimeSpan = 8;
    }
    
    //Constructor-2
    public Bomb( Position _position, int _range, int _player){
        range = _range;
        explosionTimeSpan = 3*4;
        exploded = false;
        position = _position;
        player = _player;
    }
    
    
    public int getRange(){
        return range;
    }
    
    public int getTimeLeftForExplosion(){
        return explosionTimeSpan;
    }
   
    public Position getPosition(){
        return position;
    }
    
    public int getPlayer(){
        return player;
    }
    
    //this function sets the time taken by bomb to explode
    //after it is installed.
    public void setexplosionTimeSpanForExplosion(int explosionTimeSpan){
        this.explosionTimeSpan = explosionTimeSpan;
    }
    
    //this function updates the time left for bomb to explod.
    // each time it is called it decreases the time by 1
    public void update(){
        
        if( explosionTimeSpan != 0 ){
            explosionTimeSpan--;
           
        }else if( flameTimeSpan != 0){
            flameTimeSpan--;
        }
    }
    
    public boolean isExploded(){
        
        if( explosionTimeSpan == 0 ){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isExplosionEnded(){
        if( flameTimeSpan == 0 ){
            return true;
        }else{
            return false;
        }
    }
        
}
