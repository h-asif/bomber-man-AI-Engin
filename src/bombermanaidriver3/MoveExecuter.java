/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wizard
 */
public class MoveExecuter implements Constants {

    private ArrayList<ArrayList<Integer>> map;
    private ArrayList<ArrayList<Integer>> playersProperties;
    private int player;
    private int move;
    private int playerCurrRow, playerCurrCol;
    private ArrayList<Bomb> bomb;
    private Hashtable powerToPlace;
    Random randomGenerator;
 

    public MoveExecuter(Data data) {

        map = data.getMap();
        playersProperties = data.getPlayerProperties();
        powerToPlace = data.getPowerToPlace();
        player = 1;
        move = 0;
        randomGenerator = new Random();
        
    }

    public String executeMove(int _player, int _move, Data data) {

        this.map = data.getMap();
        this.playersProperties = data.getPlayerProperties();
        this.powerToPlace = data.getPowerToPlace();
        this.bomb = data.getBombArray();
        this.player = _player;
        this.move = _move;
        this.playerCurrRow = playersProperties.get(POSITION_ROW).get(player);
        this.playerCurrCol = playersProperties.get(POSITION_COL).get(player);
        //tch   
        String newMove;

        if(playersProperties.get(IS_ALIVE).get(player) == 1 ){
            
            if (move == MOVE_UP && canMoveIn(1, 0)) {
                makeMoveOnMap(1, 0);
            } else if (move == MOVE_DOWN && canMoveIn(-1, 0)) {
                makeMoveOnMap(-1, 0);
            } else if (move == MOVE_RIGHT && canMoveIn(0, 1)) {
                makeMoveOnMap(0, 1);
            } else if (move == MOVE_LEFT && canMoveIn(0, -1)) {
                makeMoveOnMap(0, -1);
            } else if (move == PLACE_BOMB && canPlaceBomb()) {           //supposition invoved is this function is called only if player is alive
                makeMoveOnMap(1, 1);
            }else{
                return "N";
            } 

            return getStrMove();
        }
        return "K";
        
        /* these updates has to go in to main driver class
        
            // update the time remainig for explosion of bombs and and place flames if an explosion occur 
            bombExplosionUpdateMap(true);
            // remove flames if an explosion ends
            bombExplosionUpdateMap(false);
            */
        ///
       // return newMove;

    }

    private boolean canPlaceBomb() {

        if (playersProperties.get(NUM_OF_BOMBS).get(player) == 0) {
            return false;
        } else {
            return true;
        }

    }

    private boolean canMoveIn(int rowChange, int colChange) {


        if (playerCurrRow + rowChange < map.size() - 1 && playerCurrRow + rowChange > 0
                && playerCurrCol + colChange < map.get(1).size() - 1 && playerCurrCol + colChange > 0
                && map.get(playerCurrRow + rowChange).get(playerCurrCol + colChange) != UNBREAKABLE_TILE
                && map.get(playerCurrRow + rowChange).get(playerCurrCol + colChange) != BREAKABLE_TILE
                && map.get(playerCurrRow + rowChange).get(playerCurrCol + colChange) != BOMB) {

            return true;
        } else {
            return false;
        }
    }

    private String getStrMove() {

        if (move == MOVE_UP) {
            return "U";
        } else if (move == MOVE_DOWN) {
            return "D";
        } else if (move == MOVE_RIGHT) {
            return "R";
        } else if (move == MOVE_LEFT) {
            return "L";
        } else if (move == PLACE_BOMB) {
            return "B";
        } else {
            return "N";
        }
    }

    private void makeMoveOnMap(int rowChange, int colChange) {

        //places bomb
        if (rowChange == colChange && colChange == 1) {
            //placing bomb on the map
            map.get(playerCurrRow).set(playerCurrCol, BOMB);
            //decreasing no of bombs a player can place
            playersProperties.get(NUM_OF_BOMBS).set(player, playersProperties.get(NUM_OF_BOMBS).get(player) - 1);
            //adding the newly placed bob to bomb array
            bomb.add(new Bomb(playerCurrRow, playerCurrCol,
                    playersProperties.get(BOMB_RANGE).get(player), player));
            return;
        }


        //checks if the the place from where player is moved contains bomb, another player or nothing
        if (map.get(playerCurrRow).get(playerCurrCol) != BOMB) {

            int player_num = isAnyPlayerAt(playerCurrRow, playerCurrCol); // '0' means there is no player other than current player
            if (player_num == 0) {
                map.get(playerCurrRow).set(playerCurrCol, EMPTY_TILE);
            } else {
                map.get(playerCurrRow).set(playerCurrCol, player_num);
            }

        }


        //increases power of the palyer if any on the palce the player just moved to
        if (map.get(playerCurrRow + rowChange).get(playerCurrCol + colChange) > POWER_START
                && map.get(playerCurrRow + rowChange).get(playerCurrCol + colChange) < POWER_END) {

            powerUp(map.get(playerCurrRow + rowChange).get(playerCurrCol + colChange));
        }

        map.get(playerCurrRow + rowChange).set(playerCurrCol + colChange, player);



        playersProperties.get(POSITION_ROW).set(player, playerCurrRow + rowChange);
        playersProperties.get(POSITION_COL).set(player, playerCurrCol + colChange);
        playerCurrRow = playerCurrRow + rowChange;
        playerCurrCol = playerCurrCol + colChange;

    }

    private int isAnyPlayerAt(int _row, int _col) {

        for (int i = 1; i <= 4; i++) {

            if (player != i
                    && playerCurrRow == playersProperties.get(POSITION_ROW).get(i)
                    && playerCurrCol == playersProperties.get(POSITION_COL).get(i)) {
                return i;
            }

        }
        return 0;
    }

    /*
     * it takes the type of power as an argumnet and enhances the power of the
     * particular player for whom the move executer is called
     */
    private void powerUp(int power_up) {

        int maxSpeedLimit = 3;
        if (power_up == SPEED_UP && playersProperties.get(SPEED).get(player) < maxSpeedLimit) {
            playersProperties.get(SPEED).set(player, playersProperties.get(SPEED).get(player) + 1);
        } else if (power_up == BOMBS_NUM_UP) {
            playersProperties.get(NUM_OF_BOMBS).set(player, playersProperties.get(NUM_OF_BOMBS).get(player) + 1);
        } else if (power_up == BOMB_RANGE_UP) {
            playersProperties.get(BOMB_RANGE).set(player, playersProperties.get(BOMB_RANGE).get(player) + 1);
        }
    }

    private void place_remove_flame(int no, boolean placeFlame) {

        int range = bomb.get(no).getRange();
        boolean canDo_1 = true, canDo_2 = true, canDo_3 = true, canDo_4 = true;
        int bomb_row = bomb.get(no).getPosition().getRow();
        int bomb_col = bomb.get(no).getPosition().getCol();
        int power;

        if (placeFlame) {
            map.get(bomb_row).set(bomb_col, FIRE);
        }

        for (int i = 1; i <= range; i++) {

            //for fireng up the bomb
            if (canDo_1 && bomb_row + i < map.size() - 1 && map.get(bomb_row + i).get(bomb_col) != UNBREAKABLE_TILE) {
                // is any player willing to die ;)
                // power placing on breakable tile problem: it places power even if there wasnt a breakable tile there, before
                if (map.get(bomb_row + i).get(bomb_col) == BOMB) {

                    find_explode_remove_bomb(bomb_row + i, bomb_col, placeFlame);

                } else if (map.get(bomb_row + i).get(bomb_col) == BREAKABLE_TILE) {
                    canDo_1 = false;
                    //do something cascade
                    power = placePower();       // if power value is less than 0 it means there is no power to place
                    if (power > 0) {

                        powerToPlace.put(Position.getNumPosition(bomb_row + i, bomb_col, map.get(1).size()), power);
                    }
                }

                if (!bomb.get(no).isExplosionEnded()) {
                    map.get(bomb_row + i).set(bomb_col, FIRE);
                    killPlayersAt(bomb_row + i, bomb_col);
                    
                } else {

                    Object obj = powerToPlace.get(Position.getNumPosition(bomb_row + i, bomb_col, map.get(1).size()));
                    
                    if (obj == null) {
                        map.get(bomb_row + i).set(bomb_col, EMPTY_TILE);
                    } else {
                        power = (Integer) obj;
                        map.get(bomb_row + i).set(bomb_col, power);
                        powerToPlace.remove(Position.getNumPosition(bomb_row + i, bomb_col, map.get(1).size()));
                    }
                    
                }
            } else {
                canDo_1 = false;
            }

            //for firing down the bomb
            if (canDo_2 && map.get(bomb_row - i).get(bomb_col) != UNBREAKABLE_TILE && bomb_row - i > 0) {

                if (map.get(bomb_row - i).get(bomb_col) == BOMB) {

                    find_explode_remove_bomb(bomb_row - i, bomb_col, placeFlame);

                } else if (map.get(bomb_row - i).get(bomb_col) == BREAKABLE_TILE) {
                    canDo_2 = false;
                    power = placePower();       // if power value is less than 0 it means there is no power to place
                    if (power > 0) {

                        powerToPlace.put(Position.getNumPosition(bomb_row - i, bomb_col, map.get(1).size()), power);
                    }
                }

                if (!bomb.get(no).isExplosionEnded()) {
                    map.get(bomb_row - i).set(bomb_col, FIRE);
                    killPlayersAt(bomb_row - i, bomb_col);
                } else {

                    Object obj = powerToPlace.get(Position.getNumPosition(bomb_row - i, bomb_col, map.get(1).size()));
                    
                    if (obj == null) {
                        map.get(bomb_row - i).set(bomb_col, EMPTY_TILE);
                    } else {
                        power = (Integer) obj;
                        map.get(bomb_row - i).set(bomb_col, power);
                        powerToPlace.remove(Position.getNumPosition(bomb_row - i, bomb_col, map.get(1).size()));
                    }
                    

                }
            } else {
                canDo_2 = false;
            }

            //for firing to the right of bomb
            if (canDo_3 && map.get(bomb_row).get(bomb_col + i) != UNBREAKABLE_TILE && bomb_col + i < map.get(1).size() - 1) {

                if (map.get(bomb_row).get(bomb_col + i) == BOMB) {

                    find_explode_remove_bomb(bomb_row, bomb_col + i, placeFlame);

                } else if (map.get(bomb_row).get(bomb_col + i) == BREAKABLE_TILE) {
                    canDo_3 = false;
                    power = placePower();       // if power value is less than 0 it means there is no power to place
                    if (power > 0) {

                        powerToPlace.put(Position.getNumPosition(bomb_row, bomb_col + i, map.get(1).size()), power);
                    }
                }

                if (!bomb.get(no).isExplosionEnded()) {
                    map.get(bomb_row).set(bomb_col + i, FIRE);
                    killPlayersAt(bomb_row, bomb_col+i);
                } else {

                    Object obj = powerToPlace.get(Position.getNumPosition(bomb_row, bomb_col + i, map.get(1).size()));
                    
                    if (obj == null) {
                        map.get(bomb_row ).set(bomb_col+i, EMPTY_TILE);
                    } else {
                        power = (Integer) obj;
                        map.get(bomb_row ).set(bomb_col+i, power);
                        powerToPlace.remove(Position.getNumPosition(bomb_row, bomb_col + i, map.get(1).size()));
                    }
                    
                }
            } else {
                canDo_3 = false;
            }

            //for firing to the left of bomb
            if (canDo_4 && map.get(bomb_row).get(bomb_col - i) != UNBREAKABLE_TILE && bomb_col - i > 0) {

                if (map.get(bomb_row).get(bomb_col - i) == BOMB) {

                    find_explode_remove_bomb(bomb_row, bomb_col - i, placeFlame);

                } else if (map.get(bomb_row).get(bomb_col - i) == BREAKABLE_TILE) {
                    canDo_4 = false;
                    power = placePower();       // if power value is less than 0 it means there is no power to place
                    if (power > 0) {

                        powerToPlace.put(Position.getNumPosition(bomb_row, bomb_col - i, map.get(1).size()), power);
                    }
                }

                if (!bomb.get(no).isExplosionEnded()) {
                    
                    map.get(bomb_row).set(bomb_col - i, FIRE);
                    killPlayersAt(bomb_row, bomb_col-i);
                    
                } else {

                    Object obj = powerToPlace.get(Position.getNumPosition(bomb_row, bomb_col - i, map.get(1).size()));
                   
                    if (obj == null) {
                        map.get(bomb_row ).set(bomb_col-i, EMPTY_TILE);
                    } else {
                         power = (Integer) obj;
                        map.get(bomb_row ).set(bomb_col-i, power);
                        powerToPlace.remove(Position.getNumPosition(bomb_row, bomb_col - i, map.get(1).size()));
                    }
                    }
                    
            } else {
                canDo_4 = false;
            }

        }
    }

    private void find_explode_remove_bomb(int _row, int _col, boolean placeFlame) {

        Position tmpPos = new Position(_row, _col);

        for (int i = 0; i < bomb.size(); i++) {

            if (bomb.get(i).getPosition().equals(tmpPos)) {

                while (!bomb.get(i).isExploded()) {
                    bomb.get(i).update();
                }

                place_remove_flame(i, placeFlame);

            }
        }
    }

    private int placePower() {

        int rand1 = randomGenerator.nextInt(10);

        if (rand1 % 2 == 0 && randomGenerator.nextInt(10) > 3) {
            return BOMB_RANGE_UP;
        } else if ((rand1 % 3 == 0 || rand1 % 7 == 0) && randomGenerator.nextInt(10) > 4) {
            return BOMBS_NUM_UP;
        } else if (randomGenerator.nextInt(10) > 5) {
            return SPEED_UP;
        } else {
            return -1;
        }

    }
    
    private void killPlayersAt(int _row, int _col){
    
        for (int i = 1; i <= 4; i++) {

            if ( _row == playersProperties.get(POSITION_ROW).get(i)
                    && _col == playersProperties.get(POSITION_COL).get(i)) {
                
                playersProperties.get(POSITION_ROW).set(i, -1);
                playersProperties.get(POSITION_COL).set(i, -1);
                playersProperties.get(IS_ALIVE).set(i, 0);
                
            }

        }
    }
    
    

    public void bombExplosionUpdateMap(boolean placeFlame) {

        for (int i = 0; i < bomb.size(); i++) {

            if (placeFlame) {
                bomb.get(i).update();
            }

            if (bomb.get(i).isExploded()) {

                if (!bomb.get(i).isExplosionEnded() && placeFlame) {     //exploded but flames are still there

                    place_remove_flame(i, placeFlame);
                } else if (!placeFlame && bomb.get(i).isExplosionEnded()) {                                 // exploded and flames are also finished

                    place_remove_flame(i, placeFlame);
                    playersProperties.get(NUM_OF_BOMBS).set(bomb.get(i).getPlayer(),
                            playersProperties.get(NUM_OF_BOMBS).get(bomb.get(i).getPlayer()) + 1);
                    bomb.remove(i);
                }

            }
        }
    }
    
    public String getTheChangesInMap(Data data, int[][] prevMapState){
        
            int[][] currMapState = data.getMapArray();
            String change = "";
        for(int i=0; i < data.getMapTotalRows(); i++){
        
            for(int j=0; j < data.getMapTotalCols(); j++){
            
                if( currMapState[i][j] != prevMapState[i][j] ){
                    if( currMapState[i][j] == PLAYER_ONE || currMapState[i][j] == PLAYER_TWO || 
                            currMapState[i][j] == PLAYER_THREE || currMapState[i][j] == PLAYER_FOUR ){
                        continue;
                    }
                    change += new Integer(i*data.getMapTotalCols()+j).toString();
                    change +="_";
                    change += getStrRepresentation(currMapState[i][j]);
                    change +=" ";
                    
                }
            }
        }
            prevMapState = currMapState;
            return change+"\n";
        }
        
        public String getStrRepresentation(int id){
        
            if(id == Constants.PLAYER_ONE ){
                return "P1";
            }else if( id == Constants.PLAYER_TWO ){
                return "P2";
            }else if( id == Constants.PLAYER_THREE ){
                return "P3";
            }else if( id == Constants.PLAYER_FOUR ){
                return "P4";
            }else if( id == Constants.EMPTY_TILE ){
                return "ET";
            }else if( id == Constants.BREAKABLE_TILE ){
                return "BT";
            }else if( id == Constants.UNBREAKABLE_TILE ){
                return "UT";
            }else if( id == Constants.BOMB ){
                return "BM";
            }else if( id == Constants.FIRE ){
                return "FR";
            }else if( id == Constants.SPEED_UP ){
                return "SU";
            }else if( id == Constants.BOMBS_NUM_UP ){
                return "BU";
            }else if( id == Constants.BOMB_RANGE_UP ){
                return "RU";
            }
            return "NN";
        }
}
