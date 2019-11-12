import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {
        // Create graph
        GraphTests TestGraph = new GraphTests();
        Graph graph = TestGraph.SingleWeightGraph();
        Vertex source = graph.getvertex("10");
        Vertex destination = graph.getvertex("6");
        Pair<Integer, Map<Vertex, Vertex>> results = graph.ShortestDistance(source, destination);
        Vertex current = destination;
        ArrayList<Vertex> Path = new ArrayList<>();
        Path.add(destination);
        while ((current != source) && (results.getValue().get(current)!=null))
        {
            current=results.getValue().get(current);
            Path.add(0,current);
        }
        for(Vertex v : Path)
        {
            System.out.print(v.Name);
            if (v!=destination)
                System.out.print("->");
        }



    }
    public Graph SingleWeightGraph() {
        Graph singleWeightGraph = new Graph();
        final Vertex One = singleWeightGraph.addvertex("1");
        final Vertex Two = singleWeightGraph.addvertex("2");
        final Vertex Three = singleWeightGraph.addvertex("3");
        final Vertex Four = singleWeightGraph.addvertex("4");
        final Vertex Five = singleWeightGraph.addvertex("5");
        final Vertex Six = singleWeightGraph.addvertex("6");
        final Vertex Seven = singleWeightGraph.addvertex("7");
        final Vertex Eight = singleWeightGraph.addvertex("8");
        final Vertex Nine = singleWeightGraph.addvertex("9");
        final Vertex Ten = singleWeightGraph.addvertex("10");

        singleWeightGraph.newedge(One,Two,10,10);
        singleWeightGraph.newedge(One,Four,20,20);
        singleWeightGraph.newedge(One,Five,20,20);
        singleWeightGraph.newedge(One,Six,5,5);
        singleWeightGraph.newedge(One,Seven,15,15);
        singleWeightGraph.newedge(Two,Three,5,5);
        singleWeightGraph.newedge(Two,Four,10,10);
        singleWeightGraph.newedge(Three,Two,15,15);
        singleWeightGraph.newedge(Three,Four,5,5);
        singleWeightGraph.newedge(Four,Five,10,10);
        singleWeightGraph.newedge(Five,Six,5,5);
        singleWeightGraph.newedge(Seven,Six,10,10);
        singleWeightGraph.newedge(Eight,Seven,5,5);
        singleWeightGraph.newedge(Eight,One,5,5);
        singleWeightGraph.newedge(Eight,Two,20,20);
        singleWeightGraph.newedge(Nine,Eight,20,20);
        singleWeightGraph.newedge(Nine,Two,15,15);
        singleWeightGraph.newedge(Nine,Ten,10,10);
        singleWeightGraph.newedge(Ten,Two,5,5);
        singleWeightGraph.newedge(Ten,Three,5,5);

        return singleWeightGraph;
    }

    public Graph MakeSmallGraph()
    {
        Graph mygraph= new Graph();
        final Vertex A = mygraph.addvertex("A");
        final Vertex B = mygraph.addvertex("B");
        final Vertex C = mygraph.addvertex("C");
        final Vertex D = mygraph.addvertex("D");
        final Vertex E = mygraph.addvertex("E");
        final Vertex F = mygraph.addvertex("F");

        mygraph.newedge(A,B,1,2);
        mygraph.newedge(A,C,5,1);
        mygraph.newedge(A,D,4,6);
        mygraph.newedge(B,C,3,2);
        mygraph.newedge(B,D,2,3);
        mygraph.newedge(B,E,2,4);
        mygraph.newedge(C,F,1,8);
        mygraph.newedge(C,E,2,2);
        mygraph.newedge(D,F,2,7);
        mygraph.newedge(E,F,3,6);

        return mygraph;

    }
}
