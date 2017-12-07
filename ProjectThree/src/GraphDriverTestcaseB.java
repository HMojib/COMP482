//Instructor TestCase B   Project 3 COMP 482

import java.util.*;

public class GraphDriverTestcaseB{

   public static void main (String[] args)
   {  
      System.out.println("Instructor Testcase B");
      System.out.println("\nBellman Ford Shortest Paths"); 
      Graph g1 = new Graph("inputB.txt");
      g1.printGraph();
      int start = 0;

      SPPacket spp = g1.bellmanFordShortestPaths(start);
      if( spp != null)
      {
         System.out.println("\nPrint shortest paths from start vertex  = " + start);
         g1.printShortestPaths( spp );
      }
      else
         System.out.println("Graph has a negative cycle");

   }  //end main
}