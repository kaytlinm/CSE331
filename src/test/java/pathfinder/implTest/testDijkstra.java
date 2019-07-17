package pathfinder.implTest;
import org.junit.BeforeClass;
import org.junit.Test;
import graph.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class testDijkstra {
    @Rule public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

    private static Graph<String, Double> g1;
    private static Graph<String, Double> g2;
    private static Graph<String, Double> g3;
    private static Graph<String, Double> g4;

    @BeforeClass
    private static void SetUp(){
        g1 = new Graph<>();

    }

}
