/**
 * @author İbrahim Karaca
 * 
 * Time complexity: O(m+n) where m is # of edges, n is # of vertices.
 * 
 * The naive implementation of this algorithm without using a heap is O(m*n)
 * 
 * The algorithms works only when edges have nonnegative weights
 * 
 */

import java.util.*;
import java.io.*;

class Vertex{
    int id;
    int cost;
    List<Vertex> neighbours;

    public Vertex(int id){
        this.id = id;
        neighbours = new ArrayList<Vertex>();
    }
}
public class Dijkstra {
    Map<Integer,Vertex> vertices;
    String file;
    Set<Integer> done;
    int[] distances;
    int V;

    public Dijkstra(String file, int V) throws FileNotFoundException {
        this.file = file;
        vertices = new HashMap<Integer,Vertex>();
        done = new HashSet<Integer>();
        distances = new int[V+1];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[1] = 0;
        this.V = V;

        Scanner in = new Scanner(new File(file));

        for (int i = 1; i<= V; i++){
            vertices.put(i,new Vertex(i));
        }

        while (in.hasNextLine()){
            Scanner line = new Scanner(in.nextLine());

            int vid = line.nextInt();
            Vertex v = vertices.get(vid);
            while (line.hasNext()){
                String[] temp = line.next().split(",");

                int uid = Integer.parseInt(temp[0]), cost = Integer.parseInt(temp[1]);
                Vertex u = new Vertex(uid);
                u.cost = cost;
                v.neighbours.add(u);         
            }
        }
    }

    public void findShortestPath(){
        PriorityQueue<Vertex> undone = new PriorityQueue<Vertex>((a,b)->distances[a.id]-distances[b.id]);
        undone.add(vertices.get(1));

        while (!undone.isEmpty()){
            Vertex v = vertices.get(undone.poll().id);
            if (done.contains(v.id)) continue;
            done.add(v.id);

            for (int i = 0; i<v.neighbours.size(); i++){
                Vertex w = v.neighbours.get(i);
                if (!done.contains(w.id)){
                    distances[w.id] = Math.min(distances[w.id], distances[v.id] + w.cost);
                    undone.add(w);
                }
            }
        }

        for (int i = 1; i<= V; i++){
            if (distances[i] == Integer.MAX_VALUE) distances[i] = 1000000;
        }
    }

    public void reportDistances(int[] arr){
        for (int i = 0; i< arr.length; i++){
            int vertex = arr[i];
            System.out.printf("Vertex %s's shortest path: %s%n",vertex,distances[vertex]);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Dijkstra dijkstra = new Dijkstra("dijkstraData.txt", 200);
        dijkstra.findShortestPath();
        dijkstra.reportDistances(new int[]{7,37,59,82,99,115,133,165,188,197});
    }
    
}