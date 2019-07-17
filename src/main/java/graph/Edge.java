package graph;

import org.apache.commons.lang3.NotImplementedException;
/**
 * Represents an immutable <b>Edge</b> that connects <b>Node(s)</b>.
 * An edges source and destination can be the same Node.
 *
 * @spec.specfield label : String //label of Edge
 * @spec.specfield src : Node //source node from where the Edge originates
 * @spec.specfield dst : Node //destination node to where the Edge is directioned
 *
 *<p> Abstract Invariant:
 *  label != null
 *  src != null
 *  dst != null
 *</p>
 */
public class Edge <E, NodeType>{
    /**Label of this Edge*/
    private final E label;

    /**Source of this Edge*/
    private final Node<NodeType, E> src;

    /**Destination of this Edge*/
    private final Node<NodeType, E> dst;

    private boolean DEBUG;

    //Abstract Function:
    //  AF(r) such that:
    //      label = String label of this Edge
    //      src = Node from which Edge comes from
    //      dst = Node to where Edge is pointing to/connecting to
    //
    //Representation Invariant:
    //  label != null
    //  src != null
    //  dst != null
    //  this != null
    //

    /**
     * Creates a new Edge with label l, source s, and destination d
     * @param l the label of the Edge
     * @param s the source of the Edge
     * @param d the destination of the Edge
     * @spec.requires l != null
     * @spec.requires s != null
     * @spec.requires d != null
     * @spec.modifies d.InEdges
     * @spec.modifies s.OutEdges
     * @spec.effects Constructs a new Edge e where label = l, src = s and dst = d
     * @spec.effects Adds Edge e to source node's set of outgoing edges and destination node's set
     *              of incoming edges
     */
    public Edge(E l, Node<NodeType, E> s, Node<NodeType, E> d){
        this.label = l;
        this.src = s;
        this.dst = d;
        checkRep();
    }

    /**
     * Gets the label of this Edge
     *
     * @return label of this Edge
     */
    public E getLabel(){
        return this.label;
    }

    /**
     * Gets the source of this Edge
     *
     * @return source of this Edge
     */
    public Node<NodeType, E> getSrc(){
        checkRep();
        return this.src;
    }

    /**
     * Gets the destination of this Edge
     *
     * @return destination of this Edge
     */
    public Node<NodeType, E> getDst(){
        checkRep();
        return this.dst;
    }

    /**Throws an exception if the representation invariant is violated*/
    private void checkRep(){
        if(DEBUG) {
            assert (this != null);
            assert (label != null);
            assert (src != null);
            assert (dst != null);
        }
    }

}
