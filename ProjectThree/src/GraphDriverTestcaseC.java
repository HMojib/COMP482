//Instructor TestCase C   Project 3 COMP 482

import java.util.*;

public class GraphDriverTestcaseC{

   public static void main (String[] args)
   {

      System.out.println("Instructor Testcase C");
      System.out.println("\nBFS Shortest paths Shortest Paths"); 
      Graph g1 = new Graph("inputC.txt");
      g1.printGraph();
      int start = 5;
      /*
      SPPacket spp = g1.bfsShortestPaths(start);
      System.out.println("\nPrint shortest paths from start vertex  = " + start);
      g1.printShortestPaths( spp );
        */
   }  //end main
}