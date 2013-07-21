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
public class GenericPlayer {

    private int playerNo;
    private List<ArrayList<Integer>> map;
    private List<ArrayList<Integer>> playersProperties;
    protected int colPosition;
     protected int rowPosition;
    double code;
    //constructor

    public GenericPlayer(int player_no, int posX, int posY,
            List<ArrayList<Integer>> _map, List<ArrayList<Integer>> _playersProperties) {

        this.playerNo = player_no;
        playersProperties = _playersProperties;
        map = _map;
        colPosition = posX;
        rowPosition = posY;
        code = 123456789.987654321;
    }

    public GenericPlayer(int player_no, int posX, int posY, double _code,
            List<ArrayList<Integer>> _map, List<ArrayList<Integer>> _playersProperties) {

        this.playerNo = player_no;
        playersProperties = _playersProperties;
        map = _map;
        colPosition = posX;
        rowPosition = posY;
        code = _code;
    }

    public int getMapSize(List<ArrayList<Integer>> map) {
        return map.size();
    }

    public int getPlayersInfo(int player, int property) {


        if (player >= playersProperties.get(0).size() || property >= playersProperties.size()
                || player < 0 || property < 0) {
            return -1;
        }
        return playersProperties.get(property).get(playerNumIDTransfomation(player)).intValue();

    }

    public int getMapInfo(int row, int col) {

        if (col >= map.get(0).size() || row >= map.size()
                || row < 0 || col < 0) {
            return -1;
        }
        int value = map.get(row).get(col).intValue();
        if (value == Constants.PLAYER_ONE || value == Constants.PLAYER_TWO || value == Constants.PLAYER_THREE
                || value == Constants.PLAYER_FOUR) {
            return playerNumIDTransfomation(value);
        } else {
            return value;
        }
    }

    public int atRight() {

        return getMapInfo(playersProperties.get(Constants.POSITION_ROW).get(playerNo),
                playersProperties.get(Constants.POSITION_COL ).get(playerNo)+ 1);

    }

    public int atLeft() {

        return getMapInfo(playersProperties.get(Constants.POSITION_ROW).get(playerNo),
                playersProperties.get(Constants.POSITION_COL ).get(playerNo)- 1);

    }

    public int atTop() {

        return getMapInfo(playersProperties.get(Constants.POSITION_ROW ).get(playerNo)+ 1,
                playersProperties.get(Constants.POSITION_COL).get(playerNo));

    }

    public int atBottom() {

        return getMapInfo(playersProperties.get(Constants.POSITION_ROW ).get(playerNo)-1,
                playersProperties.get(Constants.POSITION_COL).get(playerNo));

    }

    public boolean isPlayerAt(int _row, int _col) {

        for (int i = 1; i <= 4; i++) {
            if (playersProperties.get(Constants.POSITION_ROW).get(i) == _row
                    && playersProperties.get(Constants.POSITION_COL).get(i) == _col) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerAt(Position pos) {

        for (int i = 1; i <= 4; i++) {
            if (playersProperties.get(Constants.POSITION_ROW).get(i) == pos.getRow()
                    && playersProperties.get(Constants.POSITION_COL).get(i) == pos.getCol()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerAt(int _row, int _col, int playerNum) {

        if (playersProperties.get(Constants.POSITION_ROW).get(playerNumIDTransfomation(playerNum)) == _row
                && playersProperties.get(Constants.POSITION_COL).get(playerNumIDTransfomation(playerNum)) == _col) {
            return true;
        }
        return false;
    }

    public boolean isPlayerAt(Position pos, int playerNum) {

        if (playersProperties.get(Constants.POSITION_ROW).get(playerNumIDTransfomation(playerNum)) == pos.getRow()
                && playersProperties.get(Constants.POSITION_COL).get(playerNumIDTransfomation(playerNum)) == pos.getCol()) {
            return true;
        }
        return false;
    }

    public boolean isBombAt(int _row, int _col) {

        if (getMapInfo(_row, _col) == Constants.BOMB) {
            return true;
        }
        return false;
    }

    public boolean isBombAt(Position pos) {
        if (getMapInfo(pos.getRow(), pos.getCol()) == Constants.BOMB) {
            return true;
        }
        return false;
    }

    public boolean isPowerUpAt(int _row, int _col) {

        if (getMapInfo(_row, _col) > Constants.POWER_START
                && getMapInfo(_row, _col) < Constants.POWER_END) {
            return true;
        }
        return false;
    }

    public boolean isPowerUpAt(Position pos) {

        if (getMapInfo(pos.getRow(), pos.getCol()) > Constants.POWER_START
                && getMapInfo(pos.getRow(), pos.getCol()) < Constants.POWER_END) {
            return true;
        }
        return false;
    }

    public boolean isBreakableTileAt(int _row, int _col) {

        if (getMapInfo(_row, _col) == Constants.BREAKABLE_TILE) {
            return true;
        }
        return false;
    }

    public boolean isBreakableTileAt(Position pos) {
        if (getMapInfo(pos.getRow(), pos.getCol()) == Constants.BREAKABLE_TILE) {
            return true;
        }
        return false;
    }

    public boolean isUnbreakableTileAt(int _row, int _col) {

        if (getMapInfo(_row, _col) == Constants.UNBREAKABLE_TILE) {
            return true;
        }
        return false;
    }

    public boolean isUnbreakableTileAt(Position pos) {
        if (getMapInfo(pos.getRow(), pos.getCol()) == Constants.UNBREAKABLE_TILE) {
            return true;
        }
        return false;
    }

    public boolean isEmptyTileAt(int _row, int _col) {

        if (getMapInfo(_row, _col) == Constants.EMPTY_TILE) {
            return true;
        }
        return false;
    }

    public boolean isEmptyTileAt(Position pos) {
        if (getMapInfo(pos.getRow(), pos.getCol()) == Constants.EMPTY_TILE) {
            return true;
        }
        return false;
    }

    public PlayerBomb[] getBombsAt( Position pos , ArrayList<PlayerBomb> bombArray) {
        
        ArrayList<PlayerBomb> bombsAt = new ArrayList<PlayerBomb>();
        
        for(int i=0; i < bombArray.size(); i++){
        
            if(bombArray.get(i).getPosition().equals(pos)){
                bombsAt.add(bombArray.get(i));
            }
        }
        
        return (PlayerBomb[])bombsAt.toArray();
    }

    
    public PlayerBomb[] getBombsAt( int _row, int _col , ArrayList<PlayerBomb> bombArray) {
        
        ArrayList<PlayerBomb> bombsAt = new ArrayList<PlayerBomb>();
        
        for(int i=0; i < bombArray.size(); i++){
        
            if( bombArray.get(i).getPosition().getRow() == _row && bombArray.get(i).getPosition().getCol() == _col ){
                bombsAt.add(bombArray.get(i));
            }
        }
        
        return (PlayerBomb[])bombsAt.toArray();
    }
    
    
    public Position getPosition(int playerNum){
        
        if( playerNum >= 1 && playerNum <= 4){
            return new Position( playersProperties.get(Constants.POSITION_ROW).get(playerNumIDTransfomation(playerNum)),
                     playersProperties.get( Constants.POSITION_COL).get( playerNumIDTransfomation(playerNum) ) );
        }
        return null;
    }
    
    public Position getPosition( PlayerBomb bomb ){
        
        if(bomb != null){
            return bomb.getPosition();
        }
        return null;
    }
    
    
   public int bombsLeft(int playerNum){
   
       if( playerNum >= 1 && playerNum <= 4){
            return playersProperties.get(Constants.NUM_OF_BOMBS).get(playerNumIDTransfomation(playerNum));
        }
       return -1;
   }
   

    public int getMyPlayerNum() {
        return playerNo;
    }


    private int playerNumIDTransfomation(int numID) {

        if (playerNo == 1) {
            return numID;
        } else if (numID == 1) {
            return playerNo;
        } else if ((numID == 2 && playerNo == 2) || (numID == 3 && playerNo == 3) || (numID == 4 && playerNo == 4)) {
            return 1;
        } else {
            return numID;
        }
    }

    public void setPlayerNumber(double _code, int playerNum) {
        if (_code == code) {
            playerNo = playerNum;
        }
    }
}
