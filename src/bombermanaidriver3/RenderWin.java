package bombermanaidriver3;


import bombermanaidriver3.Position;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shalan
 */
public class RenderWin 
{
    
   private JLabel[][] labels;
   private JPanel dispWin;
   private JFrame jf;
   private JLabel t;
   private Position[] playersPos = new Position[4];
   private int rows; int cols;
   private ArrayList<String> names;
   private  ArrayList<Icon> images;
   boolean[]bombPlaced;
   boolean[]playerAlive;
    
    public RenderWin(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        playersPos[3]= new Position(1,1);
        playersPos[2]= new Position(1,cols-2);
        playersPos[1]= new Position(rows-2,cols-2);
        playersPos[0]= new Position(rows-2,1);
        labels = new JLabel[rows][cols];
        dispWin = new JPanel(new GridLayout(rows,cols));
        jf = new JFrame();
        names = new ArrayList<>();
       images = new ArrayList<>();
            bombPlaced = new boolean[]{false,false,false,false};
            playerAlive = new boolean[]{true,true,true,true};

       names.add("P1"); names.add("P2"); names.add("P3"); names.add("P4"); names.add("ET"); names.add("BT"); names.add("UT"); names.add("FR");names.add("RU"); names.add("SU"); names.add("B"); names.add("BU");  
       
       for(int i = 0; i< names.size();i++)
       {
           images.add(new ImageIcon("pics\\"+names.get(i) +".png"));
       }
       String s = "label";
        for(int i = 1; i <=rows;i++)
        {
            for(int j=1;j<=cols;j++)
            {
               labels[i-1][j-1] = new JLabel();
               labels[i-1][j-1].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
               labels[i-1][j-1].setName(s+ (new Integer((i-1)*cols+j)).toString());
               labels[i-1][j-1].setVisible(true);
            
               dispWin.add(labels[i-1][j-1].getName(), labels[i-1][j-1]);
            }
        }
         jf.setSize(rows*100, cols*100);
          dispWin.setVisible(true);
        jf.add(dispWin);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
    public void update(String change) throws InterruptedException
    {
      String[] changeLines = change.split("\n");
      String move;
      int i;
      String[] s;
       String temp;
      ArrayList<Integer> positions = new ArrayList<>();
      ArrayList<String> changes = new ArrayList<>();
     String tText;
     int r,c;
     bombPlaced = new boolean[]{false,false,false,false};
      for(int h = 0; h < changeLines.length;h++)
      { 
          i=0;
          move ="";
       if(labels[playersPos[h].getRow()][playersPos[h].getCol()].getIcon()== images.get(names.indexOf("P"+new Integer(h+1).toString())))  labels[playersPos[h].getRow()][playersPos[h].getCol()].setIcon(null);
      while(true)
      {
          if(!Character.isLetter(changeLines[h].charAt(i)))break;
          else move+= changeLines[h].charAt(i);
          i++;
      }
      for(int j=0;j<move.length();j++)
      {
          if(move.charAt(j)=='R')playersPos[h].setCol(playersPos[h].getCol()+1);
          else if(move.charAt(j)=='L')playersPos[h].setCol(playersPos[h].getCol()-1);
          else if(move.charAt(j)=='D')playersPos[h].setRow(playersPos[h].getRow()+1);
          else if(move.charAt(j)=='U')playersPos[h].setRow(playersPos[h].getRow()-1);
         else if(move.charAt(j)=='B')
         {
             bombPlaced[h] = true;
             labels[playersPos[h].getRow()][playersPos[h].getCol()].setIcon(images.get(names.indexOf("B")));
         }
         else if(move.charAt(j) =='K') playerAlive[h] = false;
      //
       // Thread.sleep(500);
      }
      if(bombPlaced[h]==false && playerAlive[h])labels[playersPos[h].getRow()][playersPos[h].getCol()].setIcon(images.get(names.indexOf("P"+new Integer(h+1).toString())));
      s = changeLines[h].substring(i+1).split(" ");
      for( int j =0; j<s.length;j++)
      {
          i = 0;
          temp = "";
        for(; i <s[j].length();i++)
        {
          if(!Character.isDigit(s[j].charAt(i)))break;
          else temp+= s[j].charAt(i);
        }
          if(!temp.isEmpty())
          {
          positions.add(Integer.parseInt(temp));
          changes.add(s[j].substring(i+1));
          r = positions.get(positions.size()-1)/cols;
          c = positions.get(positions.size()-1)-(r*cols);
          r = (rows-1) - r;
          labels[r][c].setIcon(images.get(names.indexOf(changes.get(changes.size()-1))));
          }
      }
                   // Thread.sleep(250);
      }
      
    }
}
