import java.util.*;
import javafx.util.Pair;

public class Graph {
    private ArrayList<Vertex> Vertices = new ArrayList<>();
    public int infinity = Integer.MAX_VALUE; //Should use double Double.POSITIVE_INFINITY

    public Vertex addvertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

    public void addvertex(Vertex v) {
        Vertices.add(v);
    }
    public Vertex getvertex(String s)
    {
        for(Vertex v : Vertices )
        {
            if (v.Name==s)
                return v;
        }
        return null;
    }

    public void newedge(Vertex from, Vertex to, int dist, int tim) {
        Edge newedge=new Edge(from,to);
        newedge.distance=dist;
        newedge.time=tim;
    }

    public Pair<Integer, Map<Vertex,Vertex>> ShortestDistance(Vertex source, Vertex destination)
    {
        Map<Vertex,Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex,Vertex> PredecessorMapTime = new HashMap<>();
        Map<Vertex,Integer> DistanceMap = new HashMap<>();
        Map<Vertex,Integer> DistanceMapTime = new HashMap<>();
        Map<Vertex,Boolean> ClearMap = new HashMap<>();
        Map<Vertex,Boolean> ClearMapTime = new HashMap<>();
        // initialize arrays
        for(Vertex v: Vertices)
        {
            DistanceMap.put(v,infinity);
            DistanceMapTime.put(v,infinity);
            PredecessorMap.put(v, null);
            PredecessorMapTime.put(v,null);
            ClearMap.put(v,false);
            ClearMapTime.put(v,false);
        }
        System.out.println("Maps created.");
        DistanceMap.replace(source,0);
        DistanceMapTime.replace(source,0);
        setDistanceFromVertexEdges(source,PredecessorMap,DistanceMap,ClearMap);
        setTimeFromVertexEdges(source,PredecessorMapTime,DistanceMapTime,ClearMapTime);
        System.out.println("The shortest node path is " + DistanceMapTime.get(destination) + "nodes/vertices total.");

        return (new Pair<Integer,Map<Vertex,Vertex>> (DistanceMap.get(destination), PredecessorMap));
    }

    public void setDistanceFromVertexEdges(Vertex vertex, Map<Vertex,Vertex> predecessorMap, Map<Vertex,Integer> distanceMap, Map<Vertex,Boolean> clearMap) {
        //System.out.println("Setting distances from " + vertex.Name);
        if (!clearMap.get(vertex)) {
            for (int i = 0; i < vertex.OutEdges.size(); i++) {
                if ((vertex.OutEdges.get(i).distance + distanceMap.get(vertex)) < distanceMap.get(vertex.OutEdges.get(i).getTovertex())) {
                    distanceMap.replace(vertex.OutEdges.get(i).getTovertex(), vertex.OutEdges.get(i).distance + distanceMap.get(vertex));
                    predecessorMap.replace(vertex.OutEdges.get(i).getTovertex(), vertex);
                    //System.out.println(vertex.Name + " to " + vertex.OutEdges.get(i).getTovertex().Name + " is " + vertex.OutEdges.get(i).distance);
                    setDistanceFromVertexEdges(vertex.OutEdges.get(i).getTovertex(),predecessorMap,distanceMap,clearMap);
                }
            }
            clearMap.replace(vertex, true);
        }
    }
    public void setTimeFromVertexEdges(Vertex vertex, Map<Vertex,Vertex> predecessorMap, Map<Vertex,Integer> distanceMap, Map<Vertex,Boolean> clearMap) {
        System.out.println("Setting distances from " + vertex.Name);
        if (!clearMap.get(vertex)) {
            for (int i = 0; i < vertex.OutEdges.size(); i++) {
                if ((vertex.OutEdges.get(i).time + distanceMap.get(vertex)) < distanceMap.get(vertex.OutEdges.get(i).getTovertex())) {
                    distanceMap.replace(vertex.OutEdges.get(i).getTovertex(), vertex.OutEdges.get(i).time + distanceMap.get(vertex));
                    predecessorMap.replace(vertex.OutEdges.get(i).getTovertex(), vertex);
                    System.out.println(vertex.Name + " to " + vertex.OutEdges.get(i).getTovertex().Name + " is " + vertex.OutEdges.get(i).time);
                    setTimeFromVertexEdges(vertex.OutEdges.get(i).getTovertex(),predecessorMap,distanceMap,clearMap);
                }
            }
            clearMap.replace(vertex, true);
        }
    }
    /*public Vertex getmin(Map<Vertex,Integer> distanceMap){

        for (int i = 0; i < distanceMap.size(); i++) {
            if (distanceMap.get(i) < )
        }
        return null;
    }*/

}



class Vertex{
    public String Name;
    public ArrayList<Edge> OutEdges = new ArrayList<>();
    public  Vertex(String id){
        Name=id;
    }
    public void addOutEdge(Edge outedge){
        OutEdges.add(outedge);
    }
    public ArrayList<Edge> getOutEdges(){return OutEdges;}
}

class Edge{
    private Vertex fromvertex;
    private Vertex tovertex;
    public int distance=0;
    public int time=0;

    public Vertex getTovertex() {
        return tovertex;
    }

    public Edge(Vertex from, Vertex to){
        fromvertex=from;
        tovertex=to;
        fromvertex.addOutEdge(this);
        //If not directional
        tovertex.addOutEdge(this);
    }
}