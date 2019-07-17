package marvel.implTest;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import marvel.*;
import graph.*;
import java.util.*;
import marvel.*;

public class PathTest {
    private static Node<String, String> Ernst = null;
    private static  Node<String, String> Perkins = null;
    private static Node<String, String> Grossman = null;
    private static Node<String, String> Notkin = null;
    private static Graph<String, String> graph = null;
    private static Graph<String, String> graph2 = null;
    List<Node<String, String>> list = new LinkedList<>();

    @BeforeClass
    public static void SetUp(){
         graph = MarvelPaths.buildGraph("src/test/resources/marvel/data/staffSuperheroes.tsv");
         graph2 = MarvelPaths.buildGraph("src/test/resources/marvel/data/smallTest.tsv");
        for(String n : graph.listNodes()){
            if(n.equals("Ernst-the-Bicycling-Wizard")){
                Ernst = graph.getNode(n);
            }
            if(n.equals("Perkins-the-Magical-Singing-Instructor")){
                Perkins = graph.getNode(n);
            }
            if(n.equals("Grossman-the-Youngest-of-them-all")){
                Grossman = graph.getNode(n);
            }
            if(n.equals("Notkin-of-the-Superhuman-Beard")){
                Notkin = graph.getNode(n);
            }
        }


    }

    @Test
    public void findPathTestwithStaff(){
        list.add(Perkins);
        assertEquals("Simple path: E to P", list, MarvelPaths.findPath(graph,"Ernst-the-Bicycling-Wizard", "Perkins-the-Magical-Singing-Instructor"));

        list.clear();
        list.add(Notkin);
        assertEquals("Simple path: E to N", list, MarvelPaths.findPath(graph,"Ernst-the-Bicycling-Wizard", "Notkin-of-the-Superhuman-Beard"));

        list.clear();
        list.add(Grossman);
        assertEquals("Simple path: E to G", list, MarvelPaths.findPath(graph, "Ernst-the-Bicycling-Wizard", "Grossman-the-Youngest-of-them-all"));
    }

    @Test
    public void testBuildGraph(){
        Graph<String, String> graph = MarvelPaths.buildGraph("src/test/resources/marvel/data/staffSuperheroes.tsv");

        assertTrue("builds graph not empty of edges", !graph.listEdges().isEmpty());
        assertTrue("builds graph not empty of nodes", !graph.listNodes().isEmpty());

        Node<String, String> Ernst = null;
        Node<String, String> Perkins = null;
        Node<String, String> Grossman = null;
        Node<String, String> Notkin = null;
        for(String n : graph.listNodes()){
            if(n.equals("Ernst-the-Bicycling-Wizard")){
                Ernst = graph.getNode(n);
            }
            if(n.equals("Perkins-the-Magical-Singing-Instructor")){
                Perkins = graph.getNode(n);
            }
            if(n.equals("Grossman-the-Youngest-of-them-all")){
                Grossman = graph.getNode(n);
            }
            if(n.equals("Notkin-of-the-Superhuman-Beard")){
                Notkin = graph.getNode(n);
            }
        }
        List<Node<String, String>> list = new LinkedList<>();
        list.add(Ernst);
        list.add(Perkins);
        list.add(Grossman);
        list.add(Notkin);
        list.add(Notkin);
        list.add(Ernst);

        List<String> edge = new LinkedList<>();
        edge.add("CSE331");

        assertEquals("Connects appropriate nodes with edges", edge, graph.findConnection(Ernst, Perkins));

        edge.add("CSE332");
        edge.add("CSE341");

        assertTrue("Connects appropriate nodes with edges", graph.findConnection(Grossman, Grossman).isEmpty());
    }
}