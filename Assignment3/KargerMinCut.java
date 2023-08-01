package Assignment3;
/**
 * @author İbrahim Karaca
 * 
 * The implementation to find the minimum cut of a graph
 * using the Karger's random contraction algorithm.
 * 
 */
import java.io.*;
import java.util.*;

class Vertex{
    int id;
    ArrayList<Edge> adj;
    public Vertex(int id){
        this.id = id;
        adj = new ArrayList<Edge>();
    }
}

class Edge{
    Vertex v,u;
    public Edge(Vertex v, Vertex u){
        this.v = v;
        this.u = u;
    }
    boolean isEqual(Edge e){
        return e.v == v && e.u == u;
    }

    Vertex getOtherPoint(Vertex w){
        return w == v ? u : v;
    }
}

public class KargerMinCut {
    String file;
    Map<Integer,Vertex> vertices;
    ArrayList<Edge> edges;

    public KargerMinCut(String file) throws FileNotFoundException {
        this.file = file;
        this.vertices = new HashMap<Integer,Vertex>();
        this.edges = new ArrayList<Edge>();
        read();
    }

    void read() throws FileNotFoundException{
        Scanner in = new Scanner(new File(file));
        
        while (in.hasNextLine()){
            Scanner line = new Scanner(in.nextLine());
            int vid = line.nextInt();
            Vertex v = new Vertex(vid);
            vertices.put(vid,v);
        }

        in = new Scanner(new File(file));

        while (in.hasNextLine()){
            Scanner line = new Scanner(in.nextLine());
            int vid = line.nextInt();
            Vertex v = vertices.get(vid);

            while (line.hasNextInt()){
                int uid = line.nextInt();
                Vertex u = vertices.get(uid);
                if (vid<uid) addEdge(u,v,1);
            }
        }
    }

    void addEdge(Vertex v, Vertex u, int count){
        int vid = v.id, uid = u.id;
        Edge e = new Edge(vertices.get(Math.min(vid,uid)),vertices.get(Math.max(vid,uid)));
        while (count > 0){
            v.adj.add(e);
            u.adj.add(e);
            edges.add(e);
            count--;
        }
    }

    int removeEdge(Edge e){
        int count = 0;
        Vertex v = e.v, u = e.u;

        for (int i = 0; i < v.adj.size(); i++){
            if (v.adj.get(i).isEqual(e)) v.adj.remove(i--);
        }
        for (int i = 0; i < u.adj.size(); i++){
            if (u.adj.get(i).isEqual(e)) u.adj.remove(i--);
        }
        for (int i = 0; i < edges.size(); i++){
            if (edges.get(i).isEqual(e)){
                edges.remove(i--);
                count++;
            }
        }
        return count;
    }

    void randomContraction(){
        Random rnd = new Random();
        while (vertices.size() > 2){
            int index = rnd.nextInt(edges.size());
            Edge remove = edges.get(index);
            Vertex v = remove.v, u = remove.u;
            removeEdge(remove);
            while (u.adj.size()> 0){
                Edge temp = u.adj.get(0);
                Vertex w = temp.getOtherPoint(u);
                addEdge(v, w, removeEdge(temp));
            }
            vertices.remove(u.id);
        }
    }
    public int findMinCut() throws FileNotFoundException{
		int n = vertices.size();
		int minCut = Integer.MAX_VALUE;

        for (int i = 0; i<n; i++){
            KargerMinCut g = new KargerMinCut(this.file);
            g.randomContraction();
            int crossingEdges = g.edges.size();
            minCut = Math.min(minCut, crossingEdges);
        }
        return minCut;
	}
    public static void main(String[] args) throws FileNotFoundException {
        KargerMinCut g = new KargerMinCut("kargerMinCut.txt");
        System.out.println(g.findMinCut());
    }
}