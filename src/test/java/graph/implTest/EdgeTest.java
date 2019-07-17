package graph.implTest;

import org.junit.Test;
import graph.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * Edge class.
 */
public final class EdgeTest {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private static final Node<String, String> sourc = new Node<String, String> ("source");
    private static final Node<String, String> dest = new Node<String, String>("destination");
    private static final String label = "label";
    Edge<String, String> e = new Edge<String, String>(label,sourc,dest);
//    @BeforeClass
//    public static void setUpBeforeTests() throws Exception {
//        Edge e = new Edge(label, src, dst);
//    }

    /**Test to see that Edge returns the correct label when queried*/
    @Test
    public void testGetLabel(){
        assertEquals("e.getLabel()", label, e.getLabel());
    }

    /**Tests to see that Edge returns the correct source node when queried*/
    @Test
    public void testGetSrc(){
        assertEquals("e.getSrc()", sourc, e.getSrc());
    }

    /**Tests to see that Edge returns the correct destination node when queried*/
    @Test
    public void testGetDst(){
        assertEquals("e.getDst()", dest, e.getDst());
    }
}

