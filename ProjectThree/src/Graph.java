/* Hamoun Mojib
 * Project Three: Graphs
 * Professor Schwartz
 * COMP 482
 * December 7, 2017
 */


import java.io.File;
import java.util.*;

public class Graph
{

    //------------------------------------------------------
    private ArrayList<EdgeNode>[]  adjList;
    private int nVertices;
    private int nEdges;
    private String fileName;
    int MAX_VALUE = Integer.MAX_VALUE;

    /******************  Constructor**********************/
    public Graph ( String inputFileName){
        this.fileName = inputFileName;
        createAdjList();
    }

    /******************Print graph method***************/

    public void printGraph()
    {
        System.out.println("Graph: nVertices = " + nVertices + " nEdges = " + nEdges);
        System.out.println("Adjacency Lists");
        for(int i = 0; i < nVertices; i++){
            System.out.print("v = " + i + "  [");
            int fancyCounter = 0;
            for(int k = 0; k < adjList[i].size(); k++){
                System.out.print(adjList[i].get(k).toString());
                fancyCounter++;
                if(fancyCounter == adjList[i].size()){
                    System.out.print(" ]\n");
                }else{
                    System.out.print(", ");
                }
            }
        }
    }

    /******************* BFS Shortest paths  ******************/
    public SPPacket   bfsShortestPaths ( int start)
    {
        int currentNode = start;
        int[] distance = new int [nVertices];
        int[] parent = new int[nVertices];
        boolean[] visitedNodes = new boolean [nVertices];
        Queue<Integer> bfsQueue = new LinkedList<>();

        bfsQueue.add(currentNode);

        visitedNodes[start] = true;

        for(int i = 0; i < nVertices; i++){
            distance[i] = -1;
            parent[i] = -1;
        }

        distance[currentNode] = 0;
        parent[currentNode] = currentNode;

        while(!bfsQueue.isEmpty()){
            currentNode = bfsQueue.peek();
            bfsQueue.poll();
            visitedNodes[currentNode] = true;

            Iterator<EdgeNode> adjListIterator = adjList[currentNode].iterator();
            while(adjListIterator.hasNext()){
                Integer tempVertex = adjListIterator.next().getVertex2();

                if(!visitedNodes[tempVertex]) {
                    bfsQueue.add(tempVertex);
                    parent[tempVertex] = currentNode;
                    distance[tempVertex] = 1 + distance[currentNode];
                }
            }

        }

        SPPacket spPacket = new SPPacket(start, distance, parent);

        return spPacket;

    }

    /********************Dijkstra's Shortest Path Algorithm*** */

    public SPPacket  dijkstraShortestPaths (int start )
    {
        int currentNode = start;
        int[] distance = new int [nVertices];
        int[] parent = new int[nVertices];
        boolean[] visitedVertex = new boolean[nVertices];
        Queue<Integer> bfsQueue = new LinkedList<>();

        for(int i = 0; i < nVertices; i++){
            distance[i] = MAX_VALUE;
            parent[i] = -1;
        }

        distance[currentNode] = 0;
        parent[currentNode] = currentNode;
        bfsQueue.add(currentNode);

        while(!bfsQueue.isEmpty()){

            currentNode = bfsQueue.peek();
            bfsQueue.poll();

            ArrayList<EdgeNode> nodeArray = new ArrayList<>();

            for(int i = 0; i < adjList[currentNode].size(); i++){
                EdgeNode tempNode = adjList[currentNode].get(i);

                if(!visitedVertex[tempNode.getVertex2()]) {
                    nodeArray.add(tempNode);
                    if(distance[currentNode] + nodeArray.get(nodeArray.size() - 1).getWeight() <  distance[nodeArray.get(nodeArray.size() - 1).getVertex2()]) {
                        distance[nodeArray.get(nodeArray.size() - 1).getVertex2()] = distance[currentNode] + nodeArray.get(nodeArray.size() - 1).getWeight();
                        parent[nodeArray.get(nodeArray.size() - 1).getVertex2()] = currentNode;
                    }
                }
            }

            int min = MAX_VALUE;
            int index = 0;
            for(int i = 0; i < distance.length; i ++){
                if(!visitedVertex[i] && distance[i] <= min){
                    min = distance[i];
                    index = i;
                }

            }
            if (!visitedVertex[index]) {
                bfsQueue.add(index);
            }
            visitedVertex[currentNode] = true;
        }

        SPPacket spPacket = new SPPacket(start, distance, parent);
        return spPacket;
    }

    /********************Bellman Ford Shortest Paths ***************/
    public SPPacket bellmanFordShortestPaths(int start)
    {
        int[] distance = new int [nVertices];
        int[] parent = new int[nVertices];

        for(int i = 0; i < nVertices; i++){
            distance[i] = MAX_VALUE;
            parent[i] = -1;
        }

        distance[start] = 0;
        parent[start] = start;

        for(int i = 0; i < nVertices - 1; i++){
            for(int j = 0; j < nVertices; j++){
                for(EdgeNode node: adjList[j]){
                    if(distance[node.getVertex1()] + node.getWeight() <  distance[node.getVertex2()] && distance[node.getVertex1()] != MAX_VALUE){
                        distance[node.getVertex2()] = distance[node.getVertex1()] + node.getWeight();
                        parent[node.getVertex2()] = node.getVertex1();
                    }
                }
            }
        }

        SPPacket spPacket = null;
        boolean negCycle = false;
        for(int i = 0; i < nVertices; i++){
             for(EdgeNode node : adjList[i]){
                 if(distance[node.getVertex1()] + node.getWeight() < distance[node.getVertex2()] && distance[node.getVertex1()] != MAX_VALUE){
                     negCycle = true;
                 }else
                     negCycle = false;
             }

        }

        if(!negCycle)
            spPacket = new SPPacket(start, distance, parent);
        else
            return null;

        return spPacket;
    }

    /***********************Prints shortest paths*************************/
    public void printShortestPaths( SPPacket spp)
    {
        System.out.println("Shortest Paths from vertex " + spp.getSource() + " to vertex ");
        for(int i = 0; i < nVertices; i++){
            ArrayList<Integer>[] paths;

            paths = pathCalc(spp).clone();

            System.out.print(i + ":  [");

            Iterator<Integer> pathIterator = paths[i].iterator();
            while(pathIterator.hasNext()){
                System.out.print(pathIterator.next());
                if(pathIterator.hasNext()){
                    System.out.print(", ");
                }else{

                    if(spp.getDistance()[i] == MAX_VALUE){
                        System.out.print("]  Path Weight = " + "INFINITY\n");
                    }else
                        System.out.print("]  Path Weight = " + spp.getDistance()[i] + "\n");
                }
            }

        }

    }

    /*****************isStronglyConnected***************************/
    public boolean isStronglyConnected()
    {
        SPPacket[] parents = new SPPacket[nVertices];
        boolean isStronglyConnected = true;
        for(int i = 0; i < nVertices; i++){
            parents[i] = bfsShortestPaths(i);
        }

        for(SPPacket spPacket : parents){
            for(int parent : spPacket.getParent()){
                if(parent == -1){
                    isStronglyConnected = false;
                }
            }
        }

        return isStronglyConnected;
    }

    private ArrayList<Integer>[] pathCalc(SPPacket spp){
        ArrayList<Integer>[] paths = (ArrayList<Integer>[]) new ArrayList[nVertices];

        for(int i =0; i < paths.length; i++){
            paths[i] = new ArrayList();
        }

        for(int i = 0; i < paths.length; i++){
            int parent = spp.getParent()[i];

            if(parent >= 0){
                paths[i].add(i);

                while(parent != spp.getSource()){
                    paths[i].add(parent);
                    parent = spp.getParent()[parent];
                }

                if(parent != i) {
                    paths[i].add(parent);
                }

            }else
                paths[i].add(i);

            Collections.reverse(paths[i]);
        }

        return paths;
    }

    private void createAdjList(){
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            if(scanner.hasNextInt()){
                this.nVertices = scanner.nextInt();
            }

            adjList = (ArrayList<EdgeNode>[]) new ArrayList[nVertices];
            for(int i =0; i < adjList.length; i++){
                adjList[i] = new ArrayList();
            }
            int runCounter = 1;
            int edgeCounter = 0;
            int tempVertexOne = 0;
            int tempVertexTwo = 0;
            int tempWeight;

            while(scanner.hasNextInt()){
                edgeCounter++;

                if(runCounter == 1){
                    tempVertexOne = scanner.nextInt();
                    runCounter++;
                }else if(runCounter == 2){
                    tempVertexTwo = scanner.nextInt();
                    runCounter++;
                }else{
                    tempWeight = scanner.nextInt();
                    runCounter = runCounter/3;
                    EdgeNode tempEdgeNode = new EdgeNode(tempVertexOne, tempVertexTwo, tempWeight);
                    adjList[tempVertexOne].add(tempEdgeNode);
                }
            }

            nEdges = edgeCounter/3;

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

    public String toString(){
        return "(" + getVertex1() + ", " + getVertex2() + ", " + getWeight() + ")";
    }

}
/***********************************************/

class SPPacket
{
    int[] d;    //distance array
    int[] parent;   //parent path array
    int source; //source vertex

    public SPPacket( int start, int[] dist, int[] pp)
    {

        this.d = dist.clone();
        this.parent = pp.clone();
        this.source = start;
    }

    public int[] getDistance()
    {
        return this.d;
    }

    public int[] getParent()
    {
        return this.parent;
    }

    public int getSource()
    {
        return this.source;
    }

    public String toString()
    {
        return "Parent Array: " + getParent().toString() + "\n" + "Distance Array: " + getDistance().toString() + "\n Source Vertex: " + getSource() + "\n";
    }

}
