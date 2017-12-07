//Instructor TestCase A   Project 3 COMP 482

import java.util.*;

public class GraphDriverTestcaseA {


   public static void main (String[] args)
   {
         
      System.out.println("Instructor Testcase A");
      System.out.println("\nDijkstra Shortest Paths"); 
      Graph g1 = new Graph("inputA.txt");
      g1.printGraph();
      int start = 1;
      
      SPPacket spp = g1.dijkstraShortestPaths(start);
      System.out.println("\nPrint shortest paths from start vertex  = " + start);
      g1.printShortestPaths( spp );
      
      
      if( g1.isStronglyConnected())
        System.out.println( "\nGraph is strongly connected");
      else
         System.out.println( "\nGraph is not strongly connected");
      

   }  //end main
}