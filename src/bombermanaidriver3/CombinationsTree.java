/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bombermanaidriver3;

import java.util.ArrayList;

/**
 *
 * @author Shalan
 */
public class CombinationsTree 
{
 Integer nodeData;
 ArrayList<CombinationsTree> subTrees;
 
 public CombinationsTree(Integer nodeData, Integer mustInclude, int[] numbers, int currHeight, int maxHeight)
 {
     this.nodeData= nodeData;
     
     if (currHeight == maxHeight)return;     
     else
     {
         this.subTrees = new  ArrayList<CombinationsTree>();
         if (mustInclude !=null) 
         {
          numbers[0]= mustInclude;
          numbers[mustInclude-1]=1;
         }
         
            for(int i = 0; i <numbers.length;i++)
            {
                 this.subTrees.add(new CombinationsTree(numbers[i],null,subArray(numbers,i+1),currHeight+1,maxHeight));
                 if(mustInclude!=null && i == 0)break;
            }
     }
 }
 
 public ArrayList<ArrayList<Integer>> getCombinations(ArrayList<Integer> combinations)
 {
     if (nodeData!=null)combinations.add(this.nodeData);
     ArrayList<ArrayList<Integer>> toReturn = new ArrayList<>();
     if(this.subTrees!=null)
     {
     for(int i = 0; i < this.subTrees.size();i++)
     {
       ArrayList<ArrayList<Integer>> temp = this.subTrees.get(i).getCombinations(copyArrayList(combinations));
        for(int j = 0;j<temp.size();j++)
        {
         toReturn.add((this.subTrees.get(i).getCombinations(copyArrayList(combinations))).get(j));
        }
     }
     return toReturn;
     }
     else 
     {
         ArrayList<ArrayList<Integer>> temp = new  ArrayList<ArrayList<Integer>>();
         temp.add(combinations);
         return temp;
     }
 }
 private ArrayList<Integer> copyArrayList(ArrayList<Integer> toCopy)
 {
     ArrayList<Integer> toReturn = new ArrayList<Integer>();
 
     for(int i =0;i<toCopy.size();i++)
     {
         toReturn.add(toCopy.get(i));
        }
     return toReturn;
 
 }     
  private int[] subArray(int[]array, int indexToStart)
 {
     int[] temp = new int[array.length-indexToStart];
     for(int i = 0; i <array.length-indexToStart;i++)
     {
         temp[i] = array[i+indexToStart];
     }
     return temp;
 }

}