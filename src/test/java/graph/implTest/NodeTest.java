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
 * Node class.
 */
public class NodeTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private static Node<String, String> n = new Node<String, String>("label0");
    private static Node<String, String> p = new Node<String, String>("label0");

    private static Node<String, String> n1 = new Node<String, String>("label1");
    private static Node<String, String> n2 = new Node<String, String>("label2");
    private static Node<String, String> n3 = new Node<String, String> ("label3");

    private static Edge<String, String> in1 = new Edge<String, String>("in1", n1, n);
    private static Edge<String, String> out1 = new Edge<String, String>("out1", n, n1);
    private static Edge<String, String> in2 = new Edge<String, String>("in1", n2, n);
    private static Edge<String, String> out2 = new Edge<String, String>("out2", n, n2);
    private static Edge<String, String> in3 = new Edge<String, String>("in1", n3, n);
    private static Edge<String, String> out3 = new Edge<String, String>("out3", n, n3);
    private static Edge<String, String> out5 = new Edge<String, String>("out5", n3, n3);

    private static Edge<String, String> out4 = new Edge<String, String>("out4", n3, n2);
    private static Set<Edge<String, String>> setIn = new HashSet<>();
    private static Set<Edge<String, String>> setOut = new HashSet<>();
    private static Set<Node<String, String>> nParent = new HashSet<>();
    private static Set<Node<String, String>> nChild = new HashSet<>();
    private static LinkedList<Node<String, String>> n1Parent = new LinkedList<>();
    private static LinkedList<Node<String, String>> n1Child = new LinkedList<>();
    private static LinkedList<Node<String, String>> n2Parent = new LinkedList<>();
    private static LinkedList<Node<String, String>> n3Child = new LinkedList<>();


    private static Node<String, String> n4 = new Node<String, String>("label4");
    private static Node<String, String> n5 = new Node<String, String>("label5");
    private static Node<String, String> n6 = new Node<String, String> ("label6");
    private static Edge<String, String> d1 = new Edge<String, String> ("d1",n4, n5);
    private static Edge<String, String> d2 = new Edge<String, String> ("d2", n5,n4);
    private static Edge<String, String> d3 = new Edge<String, String> ("d3", n6, n4);
    private static Edge<String, String> d4 = new Edge<String, String>("d4", n4, n5);
    private static Set<Edge<String, String>> In4 = new HashSet<>();
    private static Set<Edge<String, String>> Out4 = new HashSet<>();
    @BeforeClass
    public static void setUpBeforeTests() throws Exception {
        setIn.add(in1);
        setOut.add(out1);
        nParent.add(n1);
        nParent.add(n2);
        nParent.add(n3);
        nChild.add(n1);
        nChild.add(n2);
        nChild.add(n3);

        n1Parent.add(n);
        n1Child.add(n);

        n2Parent.add(n);
        n2Parent.add(n3);

        n3Child.add(n2);
        n3Child.add(n);

        In4.add(d2);
        In4.add(d3);
        Out4.add(d1);
        Out4.add(d4);
    }

    /**Test to see that Node returns the correct label when queried*/
    @Test
    public void testGetLabel(){
        assertEquals("n.getLabel()", "label0", n.getLabel());
    }

//    /**Test to see that Node returns the correct set of incoming edges when queried*/
//    @Test
//    public void testInEdges(){
//        assertTrue("n.getInEdges when InEdges.isEmpty", n3.getInEdges().isEmpty());
//
//        n.addInEdge(in1);
//        assertEquals("n.getInEdges when InEdges has one incoming Edge", setIn, n.getInEdges());
//
//        n.addInEdge(in2);
//        n.addInEdge(in3);
//        setIn.add(in2);
//        setIn.add(in3);
//        assertEquals("n.getInEdges when InEdges has many incoming Edges", setIn, n.getInEdges());
//    }

    /**Test to see that Node returns the correct set of outgoing edges when queried*/
    @Test
    public void testOutEdges(){
        assertTrue("n.getOutEdges when OutEdges.isEmpty", n.getOutEdges().isEmpty());

        n.addOutEdge(out1);
        assertEquals("n.getOutEdges when OutEdges has one outgoing Edge", setOut, n.getOutEdges());

        n.addOutEdge(out2);
        n.addOutEdge(out3);
        setOut.add(out2);
        setOut.add(out3);
        assertEquals("n.getOutEdges when OutEdges has many outgoing Edges", setOut, n.getOutEdges());
    }

//    /**Tests to see that Node returns correct list of parent nodes when queried*/
//    @Test
//    public void testGetParents(){
//        assertTrue("n.getParents() when n has no parents", n2.getParents().isEmpty());
//
//        n1.addInEdge(out1);
//        assertTrue("n.getParents() when n has one parent", n1Parent.containsAll(n1.getParents()));
//
//        n2.addInEdge(out2);
//        n2.addInEdge(out4);
//        assertTrue("n.getParents() when n has many parents", n2Parent.containsAll(n2.getParents()));
//    }

    /**Tests to see that Node returns correct list of children nodes when queried*/
    @Test
    public void testGetChildren(){
        assertTrue("n.getChildren() when n has no children", n2.getChildren().isEmpty());

        n1.addOutEdge(in2);
        assertTrue("n.getChildren() when n has one child", n1Child.containsAll(n1.getChildren()));

        n3.addOutEdge(out4);
        n3.addOutEdge(in3);
        assertTrue("n.getChildren() when n has many children", n3Child.containsAll(n3.getChildren()));
    }

    /**Tests to see that Node removes the Edge passed when queried*/
    @Test
    public void testDeleteEdge(){
//        n4.addInEdge(d2);
//        n4.addInEdge(d3);
        n4.addOutEdge(d4);
        n4.addOutEdge(d1);
//
//        In4.remove(d2);
//        n4.deleteEdge(d2);
        //assertEquals("n.deleteEdge(e) when n has many incoming Edges", In4, n4.getInEdges());

        Out4.remove(d1);
        n4.deleteEdge(d1);
        assertEquals("n.deleteEdge(e) when n has many outgoing Edges", Out4, n4.getOutEdges());

//        In4.remove(d3);
//        n4.deleteEdge(d3);
        //assertTrue("n.deleteEdge(e) when n has one incoming edge", n4.getInEdges().isEmpty());

        Out4.remove(d4);
        n4.deleteEdge(d4);
        assertTrue("n.deleteEdge(e) when n has one outgoing edge", n4.getOutEdges().isEmpty());
    }

    /**Tests to see that two Nodes do and do not equal*/
    @Test
    public void testEquals(){
        assertTrue("Two Nodes do not equal each other when they have to different labels", !n1.equals(n2));
        assertTrue("Two Nodes do equal each other when they have same labels",n.equals(p));
    }

    /**Tests to see HashCode of two nodes does and does not match*/
    @Test
    public void testHashCode(){
        assertEquals("Two Nodes that do equal each other have the same hashCode", n.hashCode(), p.hashCode());
        assertNotEquals("Two Nodes that do not equal each other have different hashCode", n1.hashCode(), n2.hashCode());
    }

}
