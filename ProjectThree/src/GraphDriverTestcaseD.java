//Instructor TestCase D   Project 3 COMP 482

import java.util.*;

public class GraphDriverTestcaseD{


   public static void main (String[] args)
   {
         
      System.out.println("Instructor Testcase D");
      System.out.println("\nBellman Ford Shortest Paths"); 
      Graph g1 = new Graph("inputD.txt");
      g1.printGraph();
      int start = 0;
      /*
      SPPacket spp = g1.bellmanFordShortestPaths(start);
      if( spp != null)
      {
         System.out.println("\nPrint shortest paths from start vertex  = " + start);
         g1.printShortestPaths( spp );
      }
      else
         System.out.println("\nGraph has a negative cycle");
      
       if( g1.isStronglyConnected())
        System.out.println( "\nGraph is strongly connected");
      else
         System.out.println( "\nGraph is not strongly connected");
 */
   }  //end main
}