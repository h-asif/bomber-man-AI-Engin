/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.ArrayList;

/**
 *
 * @author Bonjour
 */
public class PlayersCollection {
    
    private ArrayList<AiMove> players;
    private static PlayersCollection playersCollection;
    private PlayersCollection(){
        players = new ArrayList<AiMove>();
        playersCollection = null;
    }
    
    public static PlayersCollection getInstance(){
    
        if(playersCollection == null){
            playersCollection = new PlayersCollection();
        }
        return playersCollection;
    }
    
    public ArrayList<AiMove> getPlayersArrayList(){
        return players;
    }
    
    
    
}
