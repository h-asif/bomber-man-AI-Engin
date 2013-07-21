/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wizard
 */
public class Test {
   
    
    public static void main(String args[])
    {
          try
          {
            BufferedReader bif = new BufferedReader(new FileReader("test.txt"));
          String change = "";
         String temp;
         RenderWin r = new RenderWin(7,7);
       while(true)
        {
              temp = bif.readLine();bif.readLine();
              if (temp == null)break;
              else if (temp.isEmpty())
              {
                  System.out.println(change);
                  r.update(change);
                  change=""; 
                  Thread.sleep(2000);
              }
              else  change +=temp+"\n";
              
              
              
          }
            }
          catch(Exception e)
          {
              System.out.println(e.getClass());
              System.out.println(e.getMessage());
          }
             int[] integers = new int[20];
            for(int i=0; i < 20; i++){
                integers[i]= i; 
            }
            
            for(int i=0; i < 20; i++){
                System.out.println( integers[i] ); 
                                
        } 
    }
}
