/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

/**
 *
 * @author Hamza
 * 
 * This class will be used as duplicated bombs for sending to the players
 *  instead of the actual Bomb class because this is a restricted version
 * of the actual Bomb class i.e. no setter functions etc.
 * 
 */
public class PlayerBomb {

    private int range, explosionTimeSpan; 
    private Position position;
    
    //Constructor
        public PlayerBomb( Position _position, int _range, int _explosionTimeSpan){
        range = _range;
        explosionTimeSpan = _explosionTimeSpan;
        position = _position;
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

}
