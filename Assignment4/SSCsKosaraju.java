/**
 * @author İbrahim Karaca
 * 
 * The Kosaraju's two pass algorithms, counts the strongly connected components of a graph.
 * The algorithm does not depend on whether tha graph if connected or not.
 * 
 * Algorithm basics: First we find the *magical* new order of the original graph
 * by using the reversed graph. Then we iterate using the magical order from N to 1
 * where N is the number of nodes.
 * 
 * In the 2nd pass, we calculate the amount of leader nodes, which equals the total 
 * number of strongly connected components.
 */
import java.io.*;
import java.util.*;

class Node{
    int id;
    List<Node> neighbours;

    public Node(int id){
        this. id = id;
        neighbours = new ArrayList<Node>();
    }

    boolean hasEdges(){
        return neighbours.size() != 0;
    }
}
public class SSCsKosaraju {
    String file;
    final int V = 875714;
    Map<Integer,Node> graph,reversed;
    int[] leaders = new int[V+1];

    public SSCsKosaraju(String file) throws FileNotFoundException {
        this.file = file;
        graph = new HashMap<Integer,Node>();
        reversed = new HashMap<Integer,Node>();
         
        Scanner in = new Scanner(new File(file));
        for (int i = 1; i<=V; i++){
            graph.put(i,new Node(i));
            reversed.put(i,new Node(i));
        }

        while (in.hasNextLine()){
            Scanner line = new Scanner(in.nextLine());
            int vid = line.nextInt();
            int uid = line.nextInt();

            graph.get(vid).neighbours.add(graph.get(uid));
            reversed.get(uid).neighbours.add(reversed.get(vid));
        }
    }
    boolean[] explored = new boolean[V+1];
    int time = 0;
    int source = -1;

    // for reversed graph, set the new order
    void DFSLoop1(){
        Stack<Node> sq = new Stack<Node>();
        for (int i = V; i>=1; i--){
            if (!explored[i]){
                Node n = reversed.get(i);
                sq.push(n);
                DFS1(sq);
            }
        }

        Map<Integer,Node> fixedGraph = new HashMap<Integer,Node>();
        for (int key : graph.keySet()){
            Node n = graph.get(key);
            fixedGraph.put(n.id,n);
        }
        graph = fixedGraph;
        explored = new boolean[V+1];
    }

    void DFS1(Stack<Node> sq){
        while (!sq.isEmpty()){
            Node v = sq.peek();
            explored[v.id] = true;
            if (v.hasEdges()){
                Node w = v.neighbours.remove(v.neighbours.size()-1);
                if (!explored[w.id]) sq.push(w);
            }
            else{
                graph.get(v.id).id = ++time;
                sq.pop();
            }
        }
    }

    // for original graph, set the leaders
    void DFSLoop2(){
        Stack<Node> sq = new Stack<Node>();
        for (int i = V; i>= 1; i--){
            if (!explored[i]){
                Node n = graph.get(i);
                source = i;
                sq.push(n);

                DFS2(sq);
            }
        }
    }


    void DFS2(Stack<Node> sq){
        while (!sq.isEmpty()){
            Node v = sq.peek();
            if (!explored[v.id]) leaders[source]--;
            explored[v.id] = true;

            if (v.hasEdges()){
                Node w = v.neighbours.remove(v.neighbours.size()-1);
                if (!explored[w.id]) sq.push(w);
            }
            else sq.pop();
        }
    }
    
    void printSSC(){
        DFSLoop1();
        DFSLoop2();

        Arrays.sort(leaders);
        int[] top5 = new int[5];

        for (int i = 0; i<Math.min(5,leaders.length); i++){
            top5[i] = -leaders[i];
        }

        for (int num : top5) System.out.println(num);
        
    }

    // for debug purposes
    void printGraphs(){
        for (int i = 1; i<= V; i++){
            Node n = graph.get(i);
            System.out.printf("Node: %s   Adj: ",n.id);
            for (int j = 0; j<n.neighbours.size(); j++){
                System.out.print(n.neighbours.get(j).id + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 1; i<= V; i++){
            Node n = reversed.get(i);
            System.out.printf("Node: %s   Adj: ",n.id);
            for (int j = 0; j<n.neighbours.size(); j++){
                System.out.print(n.neighbours.get(j).id + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {
        SSCsKosaraju graph = new SSCsKosaraju("SCC.txt");
        graph.printSSC();
    }
    
}