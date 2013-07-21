/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.*;

/**
 *
 * @author Wizard
 */
public class Data {

    /*
     * 'map' ArrayList will contain the information of map in terms of integers
     * defined in Interface Constants, moreover player's position is is not
     * necessiraly will be in the map. It can happen that player and bomb are at
     * the same position, in this case bomb will be shown in map not player.
     */
    private ArrayList<ArrayList<Integer>> map = null;
    private ArrayList<Bomb> bombArray = null;
    /*
     *
     */
    private ArrayList<ArrayList<Integer>> playersProperties = null;
    private int mapRows;
    private int mapCols;
    private int numOfPlayers;
    private int numOfProperties;
    private Hashtable powerToPlace;

    public Data(int mapRows, int mapCols, int numOfPlayers, int numOfProperties) {

        this.mapRows = mapRows;
        this.mapCols = mapCols;
        this.numOfPlayers = numOfPlayers;
        this.numOfProperties = numOfProperties;
        map = create2DArrayList(mapRows, mapCols);
        playersProperties = create2DArrayList(numOfProperties, numOfPlayers+1);
        setDefaultPlayerProperties( playersProperties );
        bombArray = new ArrayList<Bomb>();
        powerToPlace = new Hashtable();
    }

    
    public Hashtable getPowerToPlace(){
    
        return powerToPlace;
    }
    
    
    //set the value of map at ginven row nad column
    public boolean setMapData(int row, int col, int num) {
        if (row > this.mapRows || col > this.mapCols || row < 0 || col < 0) {
            return false;
        }
        map.get(row).set(col, num);
        return true;
    }

    //returns the value of given row and column
    public int getMapData(int row, int col) {
        if (row > this.mapRows || col > this.mapCols || row < 0 || col < 0) {
            return -1;
        }
        return map.get(row).get(col).intValue();
    }

    public boolean setPlayerProperty(int player, int property, int num) {
        if (property >= this.numOfProperties || player >= this.numOfPlayers || property < 0 || player <= 0) {
            return false;
        }
        playersProperties.get(property).set(player, num);
        return true;
    }

    ////returns the value of given player and column
    public boolean isPlayerHaveProperty(int player, int property) {

        if (property >= this.numOfProperties || player >= this.numOfPlayers || property < 0 || player <= 0) {
            return false;
        }
        if (playersProperties.get(property).get(player).intValue() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }

    public ArrayList<ArrayList<Integer>> getPlayerProperties() {
        return playersProperties;
    }

    public List<ArrayList<Integer>> getUnmodifiablePlayerProperties() {
        if (playersProperties != null) {
            return Collections.unmodifiableList(playersProperties);
        } else {
            return null;
        }
    }

    public List<ArrayList<Integer>> getUnmodifiableMap() {
        if (map != null) {
            return Collections.unmodifiableList(map);
        } else {
            return null;
        }
    }

    //take number of rows and columns as arguments and create a two dimensional
    //arraylist and return the list
    private ArrayList<ArrayList<Integer>> create2DArrayList(int row, int col) {

        ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < row; i++) {

            ArrayList<Integer> row_array = new ArrayList<Integer>();
            for (int j = 0; j < col; j++) {
                row_array.add(0);
            }
            array.add(row_array);
        }
        return array;
    }

    ArrayList<Bomb> getBombArray() {
        return bombArray;
    }

    ArrayList<PlayerBomb> getDuplicateBombArray(){
        
        ArrayList<PlayerBomb> dublicateBombArray = new ArrayList<PlayerBomb>();
        PlayerBomb _bomb1;
        Bomb _bomb2;
        for(int i=0; i < bombArray.size(); i++){
        
            _bomb2 = bombArray.get(i);
            if(_bomb2.isExploded())
                continue;
            _bomb1 = new PlayerBomb( _bomb2.getPosition(),  _bomb2.getRange(), _bomb2.getTimeLeftForExplosion());
            dublicateBombArray.add(_bomb1);
        }
        return dublicateBombArray;
    }
    
    private void setDefaultPlayerProperties(ArrayList<ArrayList<Integer>> playersProperties){      
        Position[] pos = new Position[4];
        pos[0]= new Position(1,1);
        pos[1]= new Position(1,mapCols-2);
        pos[2]= new Position(mapRows-2,mapCols-2);
        pos[3]= new Position(mapRows-2,1);
        
        for(int i = 0; i < numOfProperties; i++)
        {
          for(int j = 1; j <= numOfPlayers;j++)
          {
              if(i == Constants.POSITION_ROW) playersProperties.get(i).set(j,pos[j-1].getRow());
              if(i == Constants.POSITION_COL) playersProperties.get(i).set(j,pos[j-1].getCol());
              if(i == Constants.SPEED) playersProperties.get(i).set(j,1);
              if(i == Constants.BOMB_RANGE) playersProperties.get(i).set(j,1);
              if(i == Constants.NUM_OF_BOMBS) playersProperties.get(i).set(j,1);
              if(i == Constants.IS_ALIVE) playersProperties.get(i).set(j, 1);
          }
        }
    }
    
    public int[][] getMapArray(){
        
        int[][] mapArray = new int[mapRows][mapCols];
        
        for(int i=0; i < mapRows; i++){
        
            for(int j=0; j < mapCols; j++){
            
                mapArray[i][j] = map.get(i).get(j);
            }
        }
        return mapArray;
    
    }
    
    public int getMapTotalRows(){
        return mapRows;
    }
    
    public int getMapTotalCols(){
        return mapCols;
    }
    
    public  void initializeMapData(){
	// Set empty tiles all over the map
	
	for ( int i = 0 ; i < mapRows; i++ ){
		for ( int j = 0 ; j < mapCols ; j++ ){
			setMapData( i , j , Constants.EMPTY_TILE);
		}
	}
	
	// Set Breakable tiles randomly
		// mapRows == mapCols
	int total=(int) (0.40*(Math.pow((mapRows-2),2)));
	for (int i=0;i<total;i++){
		int randomNum = 1 + (int)(Math.random()*(mapCols-2));
		int randomNum2 = 1 + (int)(Math.random()*(mapCols-2));
		if( getMapData(randomNum,randomNum2)==Constants.BREAKABLE_TILE ) {
			i--;
		}else{
			setMapData( randomNum , randomNum2 , Constants.BREAKABLE_TILE);
		}
	}
	
	// Overwrite with Unbreakable tiles at alternate locations and the borders
	
	for ( int i = 0 ; i < mapRows; i+=2 ){
		for ( int j = 0 ; j < mapCols ; j+=2 ){
			setMapData( i , j , Constants.UNBREAKABLE_TILE);
		}
	}
	for ( int i = 1 ; i < mapRows; i+=2 ){
		setMapData( i , 0 , Constants.UNBREAKABLE_TILE);
		setMapData( i , mapCols-1 , Constants.UNBREAKABLE_TILE);
	}
	for ( int j = 1 ; j < mapCols; j+=2 ){
		setMapData( 0 , j , Constants.UNBREAKABLE_TILE);
		setMapData( mapRows-1 , j , Constants.UNBREAKABLE_TILE);
	}
	        
        // Set Players at corners

        setMapData( 1 , 1 , Constants.PLAYER_ONE);
        setMapData( 1 , mapCols-2 , Constants.PLAYER_TWO);
        setMapData( mapRows-2 , mapCols-2 , Constants.PLAYER_THREE);
        setMapData( mapRows-2 , 1 , Constants.PLAYER_FOUR);

        // Set the neighboring tiles of players to be empty
        
        setMapData( 2 , 1 , Constants.EMPTY_TILE);
        setMapData( 2 , mapCols-2 , Constants.EMPTY_TILE);
        setMapData( mapRows-3 , mapCols-2 , Constants.EMPTY_TILE);
        setMapData( mapRows-3 , 1 , Constants.EMPTY_TILE);
        setMapData( 1 , 2 , Constants.EMPTY_TILE);
        setMapData( 1 , mapCols-3 , Constants.EMPTY_TILE);
        setMapData( mapRows-2 , mapCols-3 , Constants.EMPTY_TILE);
        setMapData( mapRows-2 , 2 , Constants.EMPTY_TILE);

        }
}
