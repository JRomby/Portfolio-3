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

    public Vertex getvertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    public void newedge(Vertex from, Vertex to, int dist, int tim) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = tim;
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex destination) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMap = new HashMap<>();
        Map<Vertex, Boolean> ClearMap = new HashMap<>();
        // initialize distance arrays
        System.out.println("Distance Maps created.");
        for (Vertex v : Vertices) {
            DistanceMap.put(v, infinity);
            PredecessorMap.put(v, null);
            ClearMap.put(v, false);
        }
        DistanceMap.replace(source, 0);
        setDistanceFromVertexEdges(source, PredecessorMap, DistanceMap, ClearMap);

        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMap.get(destination), PredecessorMap));
    }
    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex destination) {
        Map<Vertex, Vertex> PredecessorMapTime = new HashMap<>();
        Map<Vertex, Integer> DistanceMapTime = new HashMap<>();
        Map<Vertex, Boolean> ClearMapTime = new HashMap<>();
        //initialise time arrays
        System.out.println("Time Maps created.");
        for (Vertex v : Vertices) {
            DistanceMapTime.put(v, infinity);
            PredecessorMapTime.put(v, null);
            ClearMapTime.put(v, false);
        }
        DistanceMapTime.replace(source, 0);
        setTimeFromVertexEdges(source, PredecessorMapTime, DistanceMapTime, ClearMapTime);

        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMapTime.get(destination), PredecessorMapTime));
    }

    public void setDistanceFromVertexEdges(Vertex vertex, Map<Vertex, Vertex> predecessorMap, Map<Vertex, Integer> distanceMap, Map<Vertex, Boolean> clearMap) {
        if (!clearMap.get(vertex)) {
            for (int i = 0; i < vertex.OutEdges.size(); i++) {
                if ((vertex.OutEdges.get(i).distance + distanceMap.get(vertex)) < distanceMap.get(vertex.OutEdges.get(i).getTovertex())) {
                    distanceMap.replace(vertex.OutEdges.get(i).getTovertex(), vertex.OutEdges.get(i).distance + distanceMap.get(vertex));
                    predecessorMap.replace(vertex.OutEdges.get(i).getTovertex(), vertex);
                    setDistanceFromVertexEdges(vertex.OutEdges.get(i).getTovertex(), predecessorMap, distanceMap, clearMap);
                }
            }
            clearMap.replace(vertex, true);
        }
    }

    public void setTimeFromVertexEdges(Vertex vertex, Map<Vertex, Vertex> predecessorMapTime, Map<Vertex, Integer> distanceMapTime, Map<Vertex, Boolean> clearMapTime) {
        if (!clearMapTime.get(vertex)) {
            for (int i = 0; i < vertex.OutEdges.size(); i++) {
                if ((vertex.OutEdges.get(i).time + distanceMapTime.get(vertex)) < distanceMapTime.get(vertex.OutEdges.get(i).getTovertex())) {
                    distanceMapTime.replace(vertex.OutEdges.get(i).getTovertex(), vertex.OutEdges.get(i).time + distanceMapTime.get(vertex));
                    predecessorMapTime.replace(vertex.OutEdges.get(i).getTovertex(), vertex);
                    setTimeFromVertexEdges(vertex.OutEdges.get(i).getTovertex(), predecessorMapTime, distanceMapTime, clearMapTime);
                }
            }
            clearMapTime.replace(vertex, true);
        }

    }
}

    class Vertex {
        public String Name;
        public ArrayList<Edge> OutEdges = new ArrayList<>();

        public Vertex(String id) {

            Name = id;
        }

        public void addOutEdge(Edge outedge) {

            OutEdges.add(outedge);
        }

        public ArrayList<Edge> getOutEdges() {
            return OutEdges;
        }

        public String getName() {

            return Name;
        }
    }


    class Edge {
        private Vertex fromvertex;
        private Vertex tovertex;
        public int distance = 0;
        public int time = 0;

        public Vertex getTovertex() {

            return tovertex;
        }

        public Edge(Vertex from, Vertex to) {
            fromvertex = from;
            tovertex = to;
            fromvertex.addOutEdge(this);
            //If not directional
            tovertex.addOutEdge(this);
        }
    }
