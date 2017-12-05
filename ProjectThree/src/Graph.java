/* Hamoun Mojib
 * Project Three: Graphs
 * Professor Schwartz
 * COMP 482
 * December 7, 2017
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph
{

    //------------------------------------------------------
    private ArrayList<EdgeNode>  adjList;
    private int nVertices;
    private int nEdges;
    private String fileName;

    /******************  Constructor**********************/
    public Graph ( String inputFileName){
        this.fileName = inputFileName;
    }

    /******************Print graph method***************/

    public void printGraph()
    {  }

    /******************* BFS Shortest paths  ******************/
    public SPPacket   bfsShortestPaths ( int start)
    {  }

    /********************Dijkstra's Shortest Path Algorithm*** */

    public SPPacket  dijkstraShortestPaths (int start )
    { }

    /********************Bellman Ford Shortest Paths ***************/
    public SPPacket bellmanFordShortestPaths(int start)
    {}

    /***********************Prints shortest paths*************************/
    public void printShortestPaths( SPPacket spp)
    {}

    /*****************isStronglyConnected***************************/
    public boolean isStronglyConnected()
    {}

    private void createAdjList(){
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextInt()){
                this.nVertices = scanner.nextInt();
            }

            int runCounter = 1;
            int edgeCounter = 0;
            int tempVertexOne = 0;
            int tempVertexTwo = 0;
            int tempWeight = 0;

            while(scanner.hasNextInt()){
                edgeCounter++;

                if(runCounter == 1){
                    tempVertexOne = scanner.nextInt();
                    runCounter++;
                }else if(runCounter == 2){
                    tempVertexTwo = scanner.nextInt();
                    runCounter++;
                }else{
                    EdgeNode tempEdgeNode = new EdgeNode(tempVertexOne, tempVertexTwo, tempWeight);
                    tempWeight = scanner.nextInt();
                    runCounter = runCounter/3;
                    adjList.add(tempEdgeNode); //FIX
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}//end Graph class

//place the EdgeNode class and the SPPacket class inside the Graph.java file
/*******************************************/
class EdgeNode
{
    int vertex1;
    int vertex2;
    int weight;

    public EdgeNode ( int v1, int v2, int w){
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.weight = w;
    }

    public int getVertex1() {
        return this.vertex1;
    }

    public int getVertex2() {
        return this.vertex2;
    }

    public int getWeight() {
        return this.weight;
    }


}



    public String toString(){

    }
}
/***********************************************/
class SPPacket
{
    int[] d;    //distance array
    int[] parent;   //parent path array
    int source; //source vertex

    public SPPacket( int start, int[] dist, int[] pp)
    {}

    public int[] getDistance()
    {}

    public int[] getParent()
    {}

    public int getSource()
    {}

    public String toString()
    { }

}
