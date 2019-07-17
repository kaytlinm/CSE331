package marvel.implTest;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import graph.*;
import java.util.*;
import marvel.*;

public class ParserTest {
    private static final Map<String, List<String>> m = new HashMap<>();

    @BeforeClass
    public static void SetUp(){
        m.put("CSE331", new ArrayList<String>());
        m.put("CSE332", new ArrayList<String>());
        m.put("CSE341", new ArrayList<String>());
        m.put("CSE401", new ArrayList<String>());
        m.put("CSE403", new ArrayList<String>());
        m.get("CSE331").add("Ernst-the-Bicycling-Wizard");
        m.get("CSE331").add("Notkin-of-the-Superhuman-Beard");
        m.get("CSE331").add("Perkins-the-Magical-Singing-Instructor");
        m.get("CSE331").add("Grossman-the-Youngest-of-them-all");

        m.get("CSE332").add("Grossman-the-Youngest-of-them-all");

        m.get("CSE341").add("Grossman-the-Youngest-of-them-all");

        m.get("CSE401").add("Perkins-the-Magical-Singing-Instructor");

        m.get("CSE403").add("Ernst-the-Bicycling-Wizard");
        m.get("CSE403").add("Notkin-of-the-Superhuman-Beard");

//
//        m.get("volume1").add("hero1");
//        m.get("volume1").add("hero2");
//        m.get("volume1").add("hero3");

    }

    @Test
    public void testSmallParse(){
        assertEquals("volumes equal", m, MarvelParser.parseData("src/test/resources/marvel/data/staffSuperheroes.tsv"));
    }
}
