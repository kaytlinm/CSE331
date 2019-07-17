package graph;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import org.apache.commons.lang3.NotImplementedException;
/**
 * Represents a mutable <b>Node</b> Object that can be connected to other edges using incoming and outgoing edges.
 *
 * @spec.specfield label : String //label or name of Node
 * @spec.specfield OutEdges : Set //Set of all edges leaving Node
 * @spec.specfield InEdges : Set //Set of all edges coming into Node
 *
 * <p>Abstract Invariant:
 *   this != null
 *   label != null
 *   OutEdges != null
 *   InEdges != null
 *</p>
 */
public class Node <N, EdgeType> {

    /**Mutable Set of outgoing edges of this Node*/
    private Set<Edge<EdgeType, N>> OutEdges = new HashSet<>();

    /**Mutable Set of incoming edges of this Node*/
//    private Set<Edge<EdgeType, N>> InEdges = new HashSet<>();

    /**Immutable label of this Node*/
    private final N label;

    private boolean DEBUG;
    //Abstraction Function:
    //  AF(r) such that:
    //      label = String label of this Node
    //      OutEdges = Set of all outgoing edges of Node
    //      InEdges = Set of all incoming edges of Node
    //Representation Invariant:
    //    this != null
    //    label != null
    //    OutEdges != null && !OutEdges.contains(null)
    //    InEdges != null && !InEdges.contains(null)
    //

    /**
     * Constructs a new Node with label l
     *
     * @param l the label of the Node
     * @spec.requires l != null
     * @spec.effects Constructs a new Node n where label = l
     */
    public Node(N l){
        this.label = l;
        checkRep();
    }

//    /**
//     * Adds incoming Edge e to set of incoming edges if not already an incoming edge
//     *
//     * @param e the edge to be added to InEdges
//     * @spec.requires e != null
//     * @spec.modifies this
//     * @throws IllegalArgumentException if Edge alredy an incoming edge to this node
//     * @spec.effects adds incoming Edge e to set of incoming edges to this node if not already
//     */
//    public void addInEdge(Edge<EdgeType, N> e){
//        if(InEdges.contains(e)){
//            throw new IllegalArgumentException("Edge is already an incoming edge");
//        }
//        InEdges.add(e);
//        checkRep();
//    }

    /**
     * Adds outgoing Edge e to set of outgoing edges if not already an outgoing edge
     *
     * @param e the edge to be added to OutEdges
     * @spec.requires e != null
     * @spec.modifies this
     * @throws IllegalArgumentException if Edge is already an outgoing edge from this node
     * @spec.effects adds outgoing Edge e to set of outgoing edges from this node if not already an outgoing edge
     */
    public void addOutEdge(Edge<EdgeType, N> e){
        if(OutEdges.contains(e)){
            throw new IllegalArgumentException("Edge is already an outgoing edge");
        }
//        for(Edge edg : OutEdges){
//            if(edg.getLabel().equals(e.getLabel()) && edg.getSrc().equals(e.getSrc()) && edg.getDst().equals(e.getDst())){
//                throw new IllegalArgumentException("Edge is already an outgoing edge");
//            }
//        }
        OutEdges.add(e);
        checkRep();
    }

    /**
     * Deletes Edge e from this nodes storage of edge data
     *
     * @param e the edge to be deleted
     * @spec.requires e!= null
     * @spec.modifies this
     * @throws NoSuchElementException if edge being deleted is not connected to this node
     * @spec.effects Edge e is removed from this nodes storage of edge data
     */
    public void deleteEdge(Edge<EdgeType, N> e){
//        if(InEdges.contains(e)){
//            InEdges.remove(e);
//        } else if (OutEdges.contains(e)){
//            OutEdges.remove(e);
//        }
//        if(!OutEdges.contains(e)){
//            throw new NoSuchElementException("Edge does not exist from this node");
//        } else {
            OutEdges.remove(e);
        //}
    }

    /**
     * Returns the label of this Node
     *
     * @return label of this Node
     */
    public N getLabel(){
        return this.label;
    }

//    /**
//     * Returns a list of Nodes which are parent nodes of this Node.
//     * The parent nodes of this Node are nodes from which there is an edge to this Node.
//     *
//     * @return List of parent node(s) of this Node
//     */
//    public LinkedList<Node<N, EdgeType>> getParents(){
//        LinkedList<Node<N, EdgeType>> parents = new LinkedList<>();
//        for(Edge<EdgeType, N> e : InEdges){
//            parents.add(e.getSrc());
//        }
//        checkRep();
//        return parents;
//    }

    /**
     * Returns a Set of Nodes which are child nodes of this Node
     * The child nodes of this Node are nodes to which there is an edge from this Node
     *
     * @return Set c of child nodes of this Node
     */
    public LinkedList<Node<N,EdgeType>> getChildren(){
        LinkedList<Node<N,EdgeType>> children = new LinkedList<>();
        for(Edge<EdgeType, N> e : OutEdges){
                children.add(e.getDst());
        }
        checkRep();
        return children;
    }

    /**
     *Returns a set of all current outgoing edges from this Node
     *
     * @return Set of outgoing edges
     */
    public Set<Edge<EdgeType, N>> getOutEdges(){
        Set<Edge<EdgeType, N>> out = new HashSet<>();
        for(Edge<EdgeType, N> e : OutEdges){
            out.add(e);
        }
        checkRep();
        return out;
    }

//    /**
//     * Returns a set of all current incoming edges to this Node
//     *
//     * @return Set of incoming edges
//     */
//    public Set<Edge<EdgeType, N>> getInEdges(){
//        Set<Edge<EdgeType, N>> in = new HashSet<>();
//        for(Edge<EdgeType, N> e : InEdges){
//            in.add(e);
//        }
//        checkRep();
//        return in;
//    }

    /**
     * Returns whether object is equal to this node.
     *
     * @param o object this node is being compared to
     * @return if parameter object is equal to this node
     */
    public boolean equals(Object o){
        if(o instanceof Node<?, ?>){
            Node<?, ?> n = (Node<?, ?>) o;
            return this.getLabel().equals(n.getLabel());
        } else {
            return false;
        }
    }

    /**
     * HashCode for this node.
     *
     * @return hashCode of this nodes label
     */
    public int hashCode(){
        return label.hashCode();
    }

    /**Throws an exception if representation invariant is violated*/
    public void checkRep(){
        if(DEBUG) {
            assert (this != null);
            assert (label != null);
            //assert (!InEdges.contains(null));
            assert (!OutEdges.contains(null));
            return;
        }
    }

}
