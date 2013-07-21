/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wizard
 */
public class Player extends GenericPlayer implements AiMove, Constants{

    
    /* still to be implement it is a psudo kind of prototype*/
    public Player(int player_no, int posX, int posY,List<ArrayList<Integer>> _map, List<ArrayList<Integer>> _playersProperties){
        super(player_no, posX, posY,_map, _playersProperties);
    }
    
    public Player(int player_no, int posX, int posY, double _code,List<ArrayList<Integer>> _map, List<ArrayList<Integer>> _playersProperties){
        super(player_no, posX, posY,_code, _map, _playersProperties);
    }
    
   @Override
    public int makeMove( ArrayList<PlayerBomb> bombArray) {
       // throw new UnsupportedOperationException("Not supported yet.");
        
        //*******************//
        // AI 1
        // ******************//
        
        /*if(atRight()==Constants.EMPTY_TILE)
            return Constants.MOVE_RIGHT;//move right
        else if(atTop()==Constants.EMPTY_TILE)
            return Constants.MOVE_UP;//move up
        else if(atLeft()==Constants.EMPTY_TILE)
            return Constants.MOVE_LEFT;//move left
        else if(atBottom()==Constants.EMPTY_TILE)
            return Constants.MOVE_DOWN;//move down
	else
            return Constants.PLACE_BOMB;
	*/
        //*******************//
        // AI 2
        // ******************//
        
        
        if(atRight()==Constants.EMPTY_TILE)
			return Constants.MOVE_RIGHT;//move right
		if(atRight()==Constants.BREAKABLE_TILE|| atRight()==Constants.PLAYER_TWO|| atRight()==Constants.PLAYER_THREE|| atRight()==Constants.PLAYER_FOUR){
			if(bombsLeft(Constants.PLAYER_ONE)!=0)
				return Constants.PLACE_BOMB;//place bomb
			else if(atBottom()==Constants.EMPTY_TILE)
				return Constants.MOVE_DOWN;//move down
			else if(atLeft()==Constants.EMPTY_TILE)
				return Constants.MOVE_LEFT;//move left
			else if(atTop()==Constants.EMPTY_TILE)
				return Constants.MOVE_UP;//move up
			else
				return Constants.NONE; //do nothing
		}
		if(atRight()==Constants.UNBREAKABLE_TILE||atRight()==Constants.BOMB){
			if(bombsLeft(Constants.PLAYER_ONE)!=0){
				if(atBottom()==Constants.EMPTY_TILE)
					return Constants.MOVE_DOWN;//move down
				else if(atBottom()==Constants.BREAKABLE_TILE|| atBottom()==Constants.PLAYER_TWO|| atBottom()==Constants.PLAYER_THREE|| atBottom()==Constants.PLAYER_FOUR){
					return Constants.PLACE_BOMB;
				}
				else if(atBottom()==Constants.UNBREAKABLE_TILE||atBottom()==Constants.BOMB){
					if(atLeft()==Constants.EMPTY_TILE){
						return Constants.MOVE_LEFT;
					}
					else if(atLeft()==Constants.BREAKABLE_TILE||atLeft()==Constants.PLAYER_TWO||atLeft()==Constants.PLAYER_THREE||atLeft()==Constants.PLAYER_FOUR){
						return Constants.PLACE_BOMB;
					}
					else if(atLeft()==Constants.UNBREAKABLE_TILE||atLeft()==Constants.BOMB){
						if(atTop()==Constants.EMPTY_TILE){
							return Constants.MOVE_UP;
						}
						else if(atTop()==Constants.BREAKABLE_TILE||atTop()==Constants.PLAYER_TWO||atTop()==Constants.PLAYER_THREE||atTop()==Constants.PLAYER_FOUR){
							return Constants.PLACE_BOMB;
						}
						else if(atTop()==Constants.UNBREAKABLE_TILE||atTop()==Constants.BOMB){
							//Trapped you can do nothing!
						}
					}
				}
				//else if(atTop()==Constants.EMPTY_TILE)
					//return Constants.MOVE_UP;//move up
				//else if(atLeft()==Constants.EMPTY_TILE)// no use!
					//return Constants.MOVE_LEFT;//move left
				//else
					//return Constants.NONE; //do nothing
			}
			else{
				if(atBottom()==Constants.EMPTY_TILE ||atBottom()==Constants.PLAYER_TWO||atBottom()==Constants.PLAYER_THREE||atBottom()==Constants.PLAYER_FOUR )
					return Constants.MOVE_DOWN;//move down
				else if(atLeft()==Constants.EMPTY_TILE||atLeft()==Constants.PLAYER_TWO||atLeft()==Constants.PLAYER_THREE||atLeft()==Constants.PLAYER_FOUR)
					return Constants.MOVE_LEFT;//move left
				else if(atTop()==Constants.EMPTY_TILE||atTop()==Constants.PLAYER_TWO||atTop()==Constants.PLAYER_THREE||atTop()==Constants.PLAYER_FOUR)
					return Constants.MOVE_UP;//move up
				else
					return Constants.NONE; //do nothing
			}
				
		}
		if(atRight()==Constants.BOMB){
			if(bombsLeft(Constants.PLAYER_ONE)!=0){
				if(atBottom()==Constants.EMPTY_TILE)
					return Constants.MOVE_DOWN;//move down
				else if(atTop()==Constants.EMPTY_TILE)
					return Constants.MOVE_UP;//move up
				else if(atLeft()==Constants.EMPTY_TILE)// no use!
					return Constants.MOVE_LEFT;//move left
				else
					return Constants.NONE; //do nothing
			}
			else{
				if(atBottom()==Constants.EMPTY_TILE)
					return Constants.MOVE_DOWN;//move down
				else if(atLeft()==Constants.EMPTY_TILE)
					return Constants.MOVE_LEFT;//move left
				else if(atTop()==Constants.EMPTY_TILE)
					return Constants.MOVE_UP;//move up
				else
					return Constants.NONE; //do nothing
			}
			
		}
		return Constants.NONE;//do nothing
        }
    }
    

