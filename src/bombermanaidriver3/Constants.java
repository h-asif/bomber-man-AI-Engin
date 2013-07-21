/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

/**
 *
 * @author Wizard
 */
public interface Constants {

    //player definition
    static final int PLAYER_START = 0;
    static final int PLAYER_ONE = 1;
    static final int PLAYER_TWO = 2;
    static final int PLAYER_THREE = 3;
    static final int PLAYER_FOUR = 4;
    static final int PLAYER_END = 5;
    
    //properties definition
    
    static final int POSITION_ROW=0;
    static final int POSITION_COL=1;
    static final int SPEED = 2;
    static final int NUM_OF_BOMBS = 3;
    static final int BOMB_RANGE = 4;
    static final int IS_ALIVE = 5;
    
    //map attributes definition
    static final int TILE_START = 100;
    static final int EMPTY_TILE = 101;
    static final int BREAKABLE_TILE = 102;
    static final int BOMB = 103;
    static final int FIRE =104;
    static final int UNBREAKABLE_TILE=105;
    static final int TILE_END = 106;
    
    //factors definition
    static final int FACTOR_START = 200;
    static final int NORMAL = 201;
    static final int DOUBLE=202;
    static final int FACTOR_END=203;

    
    //movement definition
    
    static final int MOVE_START = 300;
    static final int MOVE_LEFT = 301;
    static final int MOVE_RIGHT = 302;
    static final int MOVE_UP = 303;
    static final int MOVE_DOWN = 304;
    static final int PLACE_BOMB=305;
    static final int NONE = 306;
    static final int MOVE_END = 307;
    
    
    // powers definition
    
    static final int  POWER_START = 400;
    static final int SPEED_UP=401;
    static final int BOMBS_NUM_UP = 402;
    static final int BOMB_RANGE_UP=403;
    static final int POWER_END = 404;
}
