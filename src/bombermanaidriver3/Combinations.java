/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Shalan
 */
public class Combinations 
{
    private CombinationsTree ct;
    String filename = "matchesCompleted.txt";
    File file;
    ArrayList<ArrayList<Integer>> newCombinations;
    private int matchesPlayed;
    public Combinations(int n, int r) throws FileNotFoundException, IOException
    {
        file = new File(filename);
        if(!file.exists())file.createNewFile();
        int[]array = new int[n];
        for(int i = 0; i<n;i++)
        {
            array[i]=i+1;
        }
         ct = new CombinationsTree(null,null,array,0,r);
         newCombinations = this.getNewCombinations();
         matchesPlayed = 0;
          BufferedReader bif = new BufferedReader(new FileReader(filename));
            String line; 
            while(true)
            {
            line = bif.readLine();
            if(line == null)break;
            else matchesPlayed++;
            }   
    }
    private ArrayList<ArrayList<Integer>> getNewCombinations()
    {
        try
        {
        BufferedReader bif = new BufferedReader(new FileReader(filename));
        ArrayList<ArrayList<Integer>> allCombinations = ct.getCombinations(new ArrayList<Integer>());
        System.out.println(allCombinations.size());
        ArrayList<ArrayList<Integer>> combinationsToReturn = new ArrayList<>();
        String s ="";
        String line;
        while(true)
        {
            line = bif.readLine();
            if(line == null)break;
            else s+= line +"\n";
        }        
        for(int i = 0; i < allCombinations.size();i++)
        {
            line = "";
            for(int j =0;j<allCombinations.get(0).size();j++)
            {
                line += allCombinations.get(i).get(j).toString() + " ";
            }
            if(!s.contains(line))combinationsToReturn.add(allCombinations.get(i));
        }
        bif.close();
        if(combinationsToReturn.isEmpty())return null;
        else return combinationsToReturn;
        }
         catch(Exception e)
        {
            return null;
        } 
    }
    public int getMatchesPlayed() throws FileNotFoundException, IOException
    {
           
            return matchesPlayed;
    }
    public ArrayList<Integer> getCombination() throws IOException
    {
         PrintWriter pw = new PrintWriter(new FileWriter(filename));
         String line;
         line = "";
            for(int j =0;j<newCombinations.get(0).size();j++)
            {
                line += newCombinations.get(0).get(j).toString() + " ";
            }
           pw.append(line + "\n");
             matchesPlayed++;
             return newCombinations.remove(0);
        }
     
   
    }

