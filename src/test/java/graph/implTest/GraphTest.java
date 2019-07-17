package graph.implTest;
import static org.junit.Assert.*;
import graph.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * Graph class.
 */

public class GraphTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private static Graph<String, String> g0 = new Graph<String, String>();
    private static Graph<String, String> g1 = new Graph<String, String>();
    private static Graph<String, String> g2 = new Graph<String, String>();
    private static Graph<String, String> g3 = new Graph<String, String>();
    private static Graph<String, String> g4 = new Graph<String, String>();


    private static Node<String, String> A = new Node<String, String>("A");
    private static Node<String, String> B = new Node<String, String>("B");
    private static Node<String, String> C = new Node<String, String>("C");
    private static Node<String, String> D = new Node<String, String>("D");

    private static Node<String, String> E = new Node<String, String>("E");
    private static Node<String, String> F = new Node<String, String>("F");
    private static Node<String, String> G = new Node<String, String>("G");

    private static Node<String, String> J = new Node<String, String>("J");
    private static Node<String, String> K = new Node<String, String>("K");
    private static Node<String, String> L = new Node<String, String>("L");
    private static Edge<String, String> j = new Edge<String, String>("j", J, K);
    //private static Edge<String, String> k = new Edge<String, String>("k", K, L);
    private static Edge<String, String> l = new Edge<String, String>("l", L,L);
    private static Edge<String, String> m = new Edge<String, String>("m", K,K);

    private static Edge<String, String> j2 = new Edge<String, String>("j2", J, K);
    private static Edge<String, String> k2 = new Edge<String, String>("k2", K, L);
    private static Edge<String, String> l2 = new Edge<String, String>("l2", L,L);
    private static Edge<String, String> m2 = new Edge<String, String>("m2", K,K);


    private static Edge<String, String> z = new Edge<String, String>("z", E, F);
    private static Edge<String, String> g = new Edge<String, String>("g", F, G);
    private static Edge<String, String> h = new Edge<String, String>("h", E, E);
    private static Edge<String, String> a = new Edge<String, String>("a", A, B);
    private static Edge<String, String> b = new Edge<String, String>("b", B, C);
    private static Edge<String, String> c = new Edge<String, String>("c", C, D);
    private static Edge<String, String> d = new Edge<String, String>("d", D, A);
    private static Edge<String, String> f = new Edge<String, String>("f", A,A);


    private static Set<Node<String, String>> set0Nodes = new HashSet<>();
    private static Set<Edge<String, String>> set0Edges = new HashSet<>();
    private static LinkedList<Node<String, String>> setChild = new LinkedList<>();
    private static LinkedList<Node<String, String>> setParent = new LinkedList<>();
    private static Set<Edge<String, String>> delEdge = new HashSet<>();
    private static Set<Node<String, String>> delNode = new HashSet<>();

    @BeforeClass
    public static void setUpBeforeTests() throws Exception{
        set0Nodes.add(A);
        set0Edges.add(a);

        delEdge.add(z);
        delEdge.add(g);
    }

    /**Tests that Graph adds and lists Nodes n when queried*/
    @Test
    public void testListAddNodes(){
        assertTrue("g.addNode(n) when graph is empty",  g0.listNodes().isEmpty());

        g0.addNode(A.getLabel());
        assertEquals("g.addNode(n) when graph has one node", set0Nodes, g0.listNodes());

        g0.addNode(B.getLabel());
        g0.addNode(C.getLabel());
        set0Nodes.add(B);
        set0Nodes.add(C);
        assertEquals("g.addNode(n) when graph has many nodes", set0Nodes, g0.listNodes());
    }

    /**Tests that Graph adds and lists Edge e when queried*/
    @Test
    public void testListAddEdges(){
        g1.addNode(A.getLabel());
        g1.addNode(B.getLabel());
        g1.addNode(C.getLabel());
        g1.addNode(D.getLabel());

        g1.addEdge(a);
        setChild.add(B);
        setParent.add(A);
        assertEquals("g.addEdge(e) when g has no previous edges but multiple nodes", set0Edges, g1.listEdges());
        assertTrue("Verify edge connects from right source node", A.getChildren().containsAll(setChild));
        //assertTrue("Verify edge connects to right destination node", B.getParents().containsAll(setParent));
        setChild.clear();

        g1.addEdge(b);
        set0Edges.add(b);
        setChild.add(C);
        assertEquals("g.addEdge(e) when g has one previous edge and multiple node", set0Edges, g1.listEdges());
        assertTrue("Verify edge connects from right source node", B.getChildren().containsAll(setChild));
        //assertTrue("Verify edge connects to right destination node", B.getParents().containsAll(setParent));
        setChild.clear();


        g1.addEdge(c);
        g1.addEdge(d);
        set0Edges.add(c);
        set0Edges.add(d);
        setParent.add(D);
        assertEquals("g.addEdge(e) when g has multiple edges and multiple nodes", set0Edges, g1.listEdges());

        g1.addEdge(f);
        set0Edges.add(f);
        setChild.add(A);
        setChild.add(B);
        assertEquals("g.addEdge(e) when e is a self-loop and when g has multiple edges and nodes", set0Edges, g1.listEdges());
        //assertTrue("Verify self-loop edge connects correctly, parent", A.getParents().containsAll(setParent));
        assertTrue("Verify self-loop edge connects correctly, child", A.getChildren().containsAll(setChild));

    }

    /**Tests that Graph deletes Edge e when prompted*/
    @Test
    public void testDeleteEdge(){
        g2.addNode(E.getLabel());
        g2.addNode(F.getLabel());
        g2.addNode(G.getLabel());
        g2.addEdge(z);
        g2.addEdge(g);
        g2.addEdge(h);

        delNode.add(E);
        delNode.add(F);
        delNode.add(G);


        g2.deleteEdge(h);
        System.out.println(h);
        assertEquals("g.deleteEdge(e) e is a self-loop edge and graph has multiple edges", delEdge, g2.listEdges());
        assertEquals("g.deleteEdge(e) e is a self-loop edge and graph has multiple edges, nodes exist", delNode, g2.listNodes());

        g2.deleteEdge(z);
        delEdge.remove(z);
        assertEquals("g.deleteEdge(e) when graph has multiple edges", delEdge, g2.listEdges());
        assertEquals("g.deleteEdge(e) when graph has multiple edges, nodes exist", delNode, g2.listNodes());


        g2.deleteEdge(g);
        delEdge.remove(g);
        assertEquals("g.deleteEdge(e) when graph has one edge before deletion", delEdge, g2.listEdges());
        assertEquals("g.deleteEdge(e) when graph has one edge before deletion, nodes exist", delNode, g2.listNodes());
    }

    /**Tests that Graph deletes Node n and accompanying edges when prompted*/
    @Test
    public void testDeleteNode(){
        g3.addNode(J.getLabel());
        g3.addNode(K.getLabel());
        g3.addNode(L.getLabel());
        g3.addEdge(j);
        g3.addEdge(k2);
        g3.addEdge(l);
        g3.addEdge(m);

        delNode.clear();
        delNode.add(J);
        delNode.add(K);
        delNode.add(L);
        delEdge.clear();
        delEdge.add(k2);
        delEdge.add(l);
        delEdge.add(m);

        g3.deleteNode(J.getLabel());
        delNode.remove(J);
        System.out.println(j);
        assertEquals("g.deleteNode(n) when graph has many nodes and one edge", delNode, g3.listNodes());
        assertEquals("g.deleteNode(n) when graph has many nodes and one edge, verify edge deletion", delEdge, g3.listEdges());

        g3.deleteNode(L.getLabel());
        delNode.remove(L);
        delEdge.remove(l);
        delEdge.remove(k2);
        assertEquals("g.deleteNode(n) when graph has many nodes and many edges", delNode, g3.listNodes());
        assertEquals("g.deleteNode(n) when graph has many nodes and many edges, verify edge deletion", delEdge, g3.listEdges());

        g3.deleteNode(K.getLabel());
        assertTrue("g.deleteNode(n) when graph has one node", g3.listNodes().isEmpty());
        assertTrue("g.deleteNode(n) when graph has one node, edge verification", g3.listEdges().isEmpty());

    }

    /**Tests that findConnection method returns correct edge between two nodes*/
    @Test
    public void testFindConnection(){
        g4.addNode(J.getLabel());
        g4.addNode(K.getLabel());
        g4.addEdge(j2);
        LinkedList<String> l = new LinkedList<>();
        l.add("j2");
        assertEquals("Connection between one node and another with one edge", l, g4.findConnection(J,K));

        g4.addNode(L.getLabel());
        g4.addEdge(l2);
        g4.addEdge(m2);

        l.clear();
        l.add("k2");
        assertEquals("Connection between two nodes with other in and out edges", l, g4.findConnection(K,L));
        l.clear();
        l.add("l2");
        assertEquals("Connection between an edge with a self-loop", l, g4.findConnection(L,L));
        l.clear();
        assertEquals("Connection between a two nodes that arent connected", l, g4.findConnection(J,L));

    }
}
