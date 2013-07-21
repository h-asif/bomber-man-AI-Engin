/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.Random;

/**
 *
 * @author Wizard
 */
public class Maze {
    
    public int[][] maze;
    private int rows;
    private int cols;
    private Random randomCellToJoin;
    private Random shouldJoine;
    
    
    Maze( int _rows, int _cols ){
        
        //if(rows < 3 || cols < 3 )
          //  return;
        int addRow=0;
        int addCol = 0;
        
        if(_rows%2 == 0)
            addRow = 1;
        if(_cols%2 == 0)
            addCol = 1;
                    
        this.rows = _rows -  addRow;
        this.cols = _cols - addCol ;
        maze = new int[rows][cols];
        randomCellToJoin = new Random();
        shouldJoine = new Random();
    }
    
    
    private void makeMaze(){
        setDefaultValueOfMaze();
        makeCells();
        
        for(int i=1; i < rows-1; i+= 2){
            joineRowsCells(i);
        }
        //System.out.println(maze.toString());
    }
    private void joineRowsCells(int row){
        
        joineCellHorizontal(row, getAcceptableRandomNum() );
        joineCellVertical(row, getAcceptableRandomNum() );
        for(int j=1; j < cols -1; j+=2){
                if( shouldJoine.nextInt(50)%2 == 0 ){
                    joineCellHorizontal(row, j );
                    if( j == cols-2)
                        joineCellVertical(row, j );
                }
                else{
                    joineCellVertical(row, j );
                    if( row == rows-2)
                        joineCellHorizontal(row, j );
                }
            }
    }
    
    private void joineCellHorizontal(int row, int col){
        if( col != cols-2)
            maze[row][col+1]= Constants.EMPTY_TILE;
    }
    
    
    private void joineCellVertical(int row, int col){
        
        
        if(row != rows-2)
            maze[row+1][col]= Constants.EMPTY_TILE;
    } 
    
    private int getAcceptableRandomNum(){
        
        int randomNum;
        while(true){
            randomNum = randomCellToJoin.nextInt(cols-1);
            if( randomNum%2 == 1)
                break;
        }
        return randomNum;
    }
    private void makeCells(){
        for(int i=1; i < rows; i+=2){
            for(int j=1; j < cols; j+=2){
                maze[i][j] = Constants.EMPTY_TILE;
            }
        }
    }
    
    private void setDefaultValueOfMaze(){
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                maze[i][j] = Constants.UNBREAKABLE_TILE;
            }
        }
    }
    
    private void placeBreakableBriks(){
        int noOfBreakableTiles = (int) Math.round( countEmptyTiles() * 0.6 );
        for(int i=0; i < noOfBreakableTiles; i++){
            int row = randomCellToJoin.nextInt(rows);
            int col = randomCellToJoin.nextInt(cols);
            if( maze[row][col] == Constants.EMPTY_TILE)
                maze[row][col]= Constants.BREAKABLE_TILE;
        }
        
        makePlayerPositionPlayable();
    }
    
    private void makePlayerPositionPlayable(){
    }
    
    private int countEmptyTiles(){
        
        int counter = 0;
        for(int i=1; i < rows-1; i++){
            for(int j=0; j< cols-1; j++){
                if(maze[i][j] == Constants.EMPTY_TILE )
                    counter++;
            }
        }
        return counter;
    }
    
    private void placePowers(){
        
    }
    public int[][] getMaze(int _rows, int _cols){
      //  int[][] tmp = 
        makeMaze();
        placeBreakableBriks();
        //placePowers();
       // if(maze == null)
            //System.out.println("fkjhdkjgad");
        return maze;
    }
}
