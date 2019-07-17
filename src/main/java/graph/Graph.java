package graph;
import java.util.*;

/**
 * This class represents a mutable directed <b>Graph</b> Object with a unique set of unique <b>Nodes</b> and connecting <b>Edges</b>.
 * No two Nodes have the same label and no two Edges have the same source node, destination node and label.
 *
 * @spec.specfield nodes : Set //Set of all Nodes in Graph
 * @spec.specfield edges : Set //Set of all Edges in Graph
 *
 * <p>Abstract Invariant:
 *   nodes != null
 *   this != null
 *   edges != null
 *   </p>
 * */
public class Graph <NodeType, EdgeType> {

    /**Set of Nodes in this Graph*/
    private HashMap<NodeType, Node<NodeType, EdgeType>> nodes;

    /**Set of Edges in this graph*/
    private Set<Edge<EdgeType, NodeType>> edges;

    private boolean DEBUG;
    //Abstraction Function:
    //  AF(this) =
    //      nodes = set of all nodes in this Graph g
    //      edges = set of all edges in this Graph g


    //Representation Invariant:
    //  nodes != null && !nodes.contains(null)
    //  this != null
    //  edges != null && !edges.contains(null)


    /**
     * Creates a new Graph Object
     *
     * @spec.effects Creates a new Graph g
     */
    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
        checkRep();
    }

    /**
     * Adds Node n to this graph and does not allow duplicates
     *
     * @param n single node being added to this graph
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects Adds Node n to this graph if node does not already exist
     */
    public void addNode(NodeType n){
        nodes.put(n, new Node<>(n));
        checkRep();
    }

    public Node<NodeType, EdgeType> getNode(NodeType n){
        return nodes.get(n);
    }

    /**
     * Adds Edge e to this graph and does not allow two edges with same source, destination and label.
     *
     * @param e single edge being added to this graph
     * @spec.requires e != null
     * @spec.modifies this
     * @throws IllegalArgumentException if Edge already a part of this graph
     * @spec.effects Adds Edge e to this graph if edge does not already exist
     */
    public void addEdge (Edge<EdgeType, NodeType> e){
//        if(edges.contains(e)){
//            throw new IllegalArgumentException("Edge already exists");
//        }
//        if(!nodes.containsValue(e.getDst()) || !nodes.containsValue(e.getSrc())){
//            throw new NoSuchElementException("Node of destination or source does not exist");
//        }
        //if(!edges.contains(e) && )
            nodes.get(e.getSrc().getLabel()).addOutEdge(e);
            edges.add(e);

//        for(Node<NodeType, EdgeType> n : nodes.values()){
//            if(n.equals(e.getSrc())){
//                n.addOutEdge(e);
//            }
////            if (n.equals(e.getDst())){
////                n.addInEdge(e);
////            }
//        }
        checkRep();
    }

    /**
     * Deletes Node n from this graph
     *
     * @param n single node being deleted from this graph
     * @spec.requires n != null
     * @spec.modifies this
     * @throws NoSuchElementException if node is not a part of this graph
     * @spec.effects Deletes Node n from this graph by removing node from this.nodes
     */
    public void deleteNode(NodeType n){
        if(nodes.containsKey(n)) {
//            for(Edge<EdgeType, NodeType> e : nodes.get(n).getOutEdges()){
//                nodes.get(n).deleteEdge(e);
//                edges.remove(e);
//            }
            List<Edge<EdgeType, NodeType>> list = new LinkedList<>(edges);
            for(Edge<EdgeType, NodeType> e : list){
                if(e.getDst().getLabel().equals(n) || e.getSrc().getLabel().equals(n)){
                    nodes.get(n).deleteEdge(e);
                    edges.remove(e);                }
            }
            nodes.remove(n);
            checkRep();
//            List<Edge<EdgeType, NodeType>> list = new LinkedList<>(edges);
//            for(Edge<EdgeType, NodeType> e : list){
//                if(n..getOutEdges().contains(e)) {
//                    n.deleteEdge(e);
//                    edges.remove(e);
//                }
//            }
        }else {
            throw new NoSuchElementException("Node is not part of graph");
        }

    }

    /**
     * Deletes Edge e from this graph
     *
     * @param e single edge being deleted from this graph
     * @spec.requires e != null
     * @spec.modifies this
     * @spec.effects Deletes Edge e from this graph by removing e from e.getSrc OutEdge Set and e.getDst InEdge Set
     */
    public void deleteEdge(Edge<EdgeType, NodeType> e) {
        for(Node<NodeType, EdgeType> n : nodes.values()){
            if(n.getOutEdges().contains(e)) {
                n.deleteEdge(e);
            }
        }
        edges.remove(e);
//        for(Node<NodeType, EdgeType> n : nodes) {
//            if (n.getInEdges().contains(e) || n.getOutEdges().contains(e)) {
//                n.deleteEdge(e);
//                edges.remove(e);
//            }
//        }
    }

    /**
     * Returns the Edge label of the edge that connects the two passed nodes.
     *
     * @param start single node from where connection starts
     * @param end single node where connection finishes
     * @spec.requires start != null
     * @spec.requires end != null
     * @return edge label that represent the connection from start node to end node
     */
    public List<EdgeType> findConnection(Node<NodeType, EdgeType> start, Node<NodeType, EdgeType> end) {
        List<EdgeType> list = new LinkedList<>();
        for(Edge<EdgeType, NodeType> e : start.getOutEdges()){
            if(e.getDst().equals(end)){
                list.add(e.getLabel());
            }
        }
        return list;
    }

    /**
     * Returns a Set of all current nodes in this graph
     *
     * @return Set of all nodes currently a part of this graph
     */
    public Set<NodeType> listNodes(){
        Set<NodeType> res = new HashSet<>(nodes.keySet());
        checkRep();
        return res;
    }

    /**
     * Returns a Set of all current edges in this graph
     *
     * @return Set of all edges currently in this graph
     */
    public Set<Edge<EdgeType, NodeType>> listEdges(){
        Set<Edge<EdgeType, NodeType>> edg = new HashSet<>(edges);
        checkRep();
        return edg;
    }

    /**Throws exception if representation invariant is violated*/
    private void checkRep(){
        if (DEBUG) {
            assert (nodes != null);
            assert (edges != null);
            assert (!nodes.containsKey(null));
            assert(!nodes.containsValue(null));
            assert (!edges.contains(null));
            return;
        }
    }
}
