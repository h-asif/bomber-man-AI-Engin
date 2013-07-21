/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Wizard
 */
public class BomberManAiDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int size = 7;
        
        Data data = new Data(size,size,4,6);
        
        MoveExecuter moveExecuter = new MoveExecuter(data);
        data.initializeMapData();
        int[][] prevMapState = new int[size][size];
        prevMapState = data.getMapArray();
        AiMove player1, player2, player3, player4;
       
        player1 = new Player(1, 1,1, data.getUnmodifiableMap(), data.getUnmodifiablePlayerProperties());
        player2 = new Player(2, 1,size-2, data.getUnmodifiableMap(), data.getUnmodifiablePlayerProperties());
        player3 = new Player(3, size-2,size-2, data.getUnmodifiableMap(), data.getUnmodifiablePlayerProperties());
        player4 = new Player(4, size-2,1, data.getUnmodifiableMap(), data.getUnmodifiablePlayerProperties());
        //Assignplayers();
        
        String move;
        FileWriter outFile; 
        PrintWriter out= null;
        try {
              outFile = new FileWriter( "test.txt");
              out = new PrintWriter(outFile);

          } catch (IOException e){
              e.printStackTrace();
          }
        
        int cntr = 0;
        String str="";
                for(int k=data.getMapTotalRows()-1; k >=0 ; k--){
                    str = "";
                    for(int l=0; l < data.getMapTotalRows(); l++){
                        str +=moveExecuter.getStrRepresentation( prevMapState[k][l] )+" " ;
                    }
                    System.out.println(str);
                }
                System.out.println("************************");
        while(true){
            
            move = "";
            for(int i=0; i < data.getPlayerProperties().get(Constants.SPEED).get(1); i++){
                if(player1 != null)
                move += moveExecuter.executeMove(1,player1.makeMove(data.getDuplicateBombArray()),data);
                
            }
            getAndWriteTheChanges(data, moveExecuter, out, move);
            
            move="";
            for(int i=0; i < data.getPlayerProperties().get(Constants.SPEED).get(2); i++){
            if(player2 != null)
                move += moveExecuter.executeMove(2,player2.makeMove( data.getDuplicateBombArray()), data);
            }
            
            getAndWriteTheChanges(data, moveExecuter, out, move);
            
            
            move = "";
            for(int i=0; i < data.getPlayerProperties().get(Constants.SPEED).get(1); i++){
            if(player3 != null)
                move += moveExecuter.executeMove(3,player3.makeMove(data.getDuplicateBombArray()), data);
            }
            
            getAndWriteTheChanges(data, moveExecuter, out, move);
            
            move = "";
            for(int i=0; i < data.getPlayerProperties().get(Constants.SPEED).get(1); i++){
            if(player4 != null)
                move += moveExecuter.executeMove(4,player4.makeMove( data.getDuplicateBombArray()), data);
            }
            
             getAndWriteTheChanges(data, moveExecuter, out, move);
            
           
                if(out == null)
                    System.out.println("file opening error");
                else
                out.println("\n");
                cntr++;
                prevMapState = data.getMapArray();
                 str="";
                for(int k=data.getMapTotalRows()-1; k >=0 ; k--){
                    str = "";
                    for(int l=0; l < data.getMapTotalRows(); l++){
                        str +=moveExecuter.getStrRepresentation( prevMapState[k][l] )+" " ;
                    }
                    System.out.println(str);
                }
                System.out.println("************************");
                if(cntr == 100){ //winnign conditio check
                    break;
                }

        }
        out.close();
        
     }
    
    private static void getAndWriteTheChanges(Data data, MoveExecuter moveExecuter, PrintWriter out, String move){
    
        int[][] prevMapState;
        prevMapState = data.getMapArray();
             // update the time remainig for explosion of bombs and and place flames if an explosion occur 
            moveExecuter.bombExplosionUpdateMap(true);
            // remove flames if an explosion ends
            moveExecuter.bombExplosionUpdateMap(false);
            
            move +="%";
            move+=moveExecuter.getTheChangesInMap( data, prevMapState);
            out.println(move);
    }
    
        
}
