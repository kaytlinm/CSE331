package pathfinder;
import graph.*;

import java.util.PriorityQueue;
import pathfinder.datastructures.*;
import java.util.*;


public class Dijkstra {
    public static <NodeType, EdgeType extends Number> Path<NodeType> findDijkstra(Graph<NodeType, EdgeType> graph, NodeType s, NodeType d){

        Comparator<Path<NodeType>> cost = Comparator.comparing(Path::getCost);
        PriorityQueue<Path<NodeType>> active = new PriorityQueue<>(cost);

        Set<NodeType> finished = new HashSet<>();

        Path<NodeType> f = new Path<>(s);

        active.add(f);

        while(!active.isEmpty()){
            Path<NodeType> minPath = active.remove();
            NodeType minDest = minPath.getEnd();

            if(minDest.equals(d)){
                return minPath;
            }

            if(finished.contains(minDest)) {
                continue;
            }
            for (Edge<EdgeType, NodeType> e : graph.getNode(minDest).getOutEdges()) {
                if (!finished.contains(e.getDst())) {
                    Path<NodeType> newPath = minPath.extend(e.getDst().getLabel(), (Double) e.getLabel());
                    active.add(newPath);
                }
            }

            finished.add(minDest);
        }
        return null;
    }
}
