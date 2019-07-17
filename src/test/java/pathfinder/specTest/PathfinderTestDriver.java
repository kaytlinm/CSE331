package pathfinder.specTest;

import graph.Graph;
import graph.*;
import pathfinder.*;
import pathfinder.datastructures.*;
//import graph.Node;

import java.io.*;
import java.util.*;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph.
 **/
public class PathfinderTestDriver {

  public static void main(String args[]) {
    try {
      if (args.length > 1) {
        printUsage();
        return;
      }

      PathfinderTestDriver td;

      if (args.length == 0) {
        td = new PathfinderTestDriver(new InputStreamReader(System.in),
                new OutputStreamWriter(System.out));
      } else {

        String fileName = args[0];
        File tests = new File(fileName);

        if (tests.exists() || tests.canRead()) {
          td = new PathfinderTestDriver(new FileReader(tests),
                  new OutputStreamWriter(System.out));
        } else {
          System.err.println("Cannot read from " + tests.toString());
          printUsage();
          return;
        }
      }

      td.runTests();

    } catch (IOException e) {
      System.err.println(e.toString());
      e.printStackTrace(System.err);
    }
  }

  private static void printUsage() {
    System.err.println("Usage:");
    System.err.println("to read from a file: java graph.specTest.GraphTestDriver <name of input script>");
    System.err.println("to read from standard in: java graph.specTest.GraphTestDriver");
  }

  /**
   * String -> Graph: maps the names of graphs to the actual graph
   **/
  private final Map<String, Graph<String, Double>> graphs = new HashMap<>();
  private final PrintWriter output;
  private final BufferedReader input;

  /**
   * @requires r != null && w != null
   * @effects Creates a new GraphTestDriver which reads command from
   * <tt>r</tt> and writes results to <tt>w</tt>.
   **/
  public PathfinderTestDriver(Reader r, Writer w) {
    input = new BufferedReader(r);
    output = new PrintWriter(w);
  }

  /**
   * @throws IOException if the input or output sources encounter an IOException
   * @effects Executes the commands read from the input and writes results to the output
   **/
  public void runTests()
          throws IOException {
    String inputLine;
    while ((inputLine = input.readLine()) != null) {
      if ((inputLine.trim().length() == 0) ||
              (inputLine.charAt(0) == '#')) {
        // echo blank and comment lines
        output.println(inputLine);
      } else {
        // separate the input line on white space
        StringTokenizer st = new StringTokenizer(inputLine);
        if (st.hasMoreTokens()) {
          String command = st.nextToken();

          List<String> arguments = new ArrayList<>();
          while (st.hasMoreTokens()) {
            arguments.add(st.nextToken());
          }

          executeCommand(command, arguments);
        }
      }
      output.flush();
    }
  }

  private void executeCommand(String command, List<String> arguments) {
    try {
      if (command.equals("CreateGraph")) {
        createGraph(arguments);
      } else if (command.equals("AddNode")) {
        addNode(arguments);
      } else if (command.equals("AddEdge")) {
        addEdge(arguments);
      } else if (command.equals("ListNodes")) {
        listNodes(arguments);
      } else if (command.equals("ListChildren")) {
        listChildren(arguments);
      } else if (command.equals("FindPath")){
        findPath(arguments);
      } else {
        output.println("Unrecognized command: " + command);
      }
    } catch (Exception e) {
      output.println("Exception: " + e.toString());
    }
  }

  private void createGraph(List<String> arguments) {
    if (arguments.size() != 1) {
      throw new CommandException("Bad arguments to CreateGraph: " + arguments);
    }

    String graphName = arguments.get(0);
    createGraph(graphName);
  }

  private void createGraph(String graphName) {
    // Insert your code here.

    graphs.put(graphName, new Graph<>());
    output.println("created graph " + graphName);
  }

  private void addNode(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new CommandException("Bad arguments to addNode: " + arguments);
    }

    String graphName = arguments.get(0);
    String nodeName = arguments.get(1);

    addNode(graphName, nodeName);
  }

  private void addNode(String graphName, String nodeName) {
    // Insert your code here.

    Graph<String, Double> g = graphs.get(graphName);
    //Node<String, Double> n = new Node<>(nodeName);
    g.addNode(nodeName);
    output.println("added node " + nodeName + " to " + graphName);
  }

  private void addEdge(List<String> arguments) {
    if (arguments.size() != 4) {
      throw new CommandException("Bad arguments to addEdge: " + arguments);
    }

    String graphName = arguments.get(0);
    String parentName = arguments.get(1);
    String childName = arguments.get(2);
    Double edgeLabel = Double.valueOf(arguments.get(3));

    addEdge(graphName, parentName, childName, edgeLabel);
  }

  private void addEdge(String graphName, String parentName, String childName,
                       Double edgeLabel) {
    // Insert your code here.

    Graph<String, Double> g = graphs.get(graphName);
    if (parentName.equals(childName)) {
      Node<String, Double> s = new Node<String, Double>(parentName);
      Edge<Double, String> e = new Edge<Double, String>(edgeLabel, s, s);
      g.addEdge(e);
    } else if (!parentName.equals(childName)) {
      Node<String, Double> p = new Node<>(parentName);
      Node<String, Double> c = new Node<>(childName);
      Edge<Double, String> e = new Edge<>(edgeLabel, p, c);
      g.addEdge(e);
    }
    output.println("added edge " + String.format("%.3f", edgeLabel) + " from " + parentName + " to " + childName + " in " + graphName);
  }

  private void listNodes(List<String> arguments) {
    if (arguments.size() != 1) {
      throw new CommandException("Bad arguments to listNodes: " + arguments);
    }

    String graphName = arguments.get(0);
    listNodes(graphName);
  }

  private void listNodes(String graphName) {
    // Insert your code here.

    Graph<String, Double> g = graphs.get(graphName);

    String res = "";
    Set<String> s = new HashSet<>();
    for (String n : g.listNodes()) {
      s.add(n);
    }

    if (s.isEmpty()) {
      res = " ";
    } else {
      Iterator<String> itr = s.iterator();
      while (itr.hasNext()) {
        res += " " + itr.next();
      }
    }
    output.println(graphName + " contains:" + res);

  }

  private void listChildren(List<String> arguments) {
    if (arguments.size() != 2) {
      throw new CommandException("Bad arguments to listChildren: " + arguments);
    }

    String graphName = arguments.get(0);
    String parentName = arguments.get(1);
    listChildren(graphName, parentName);
  }

  private void listChildren(String graphName, String parentName) {
    Graph<String, Double> g = graphs.get(graphName);
    Node<String, String> p = new Node<>(parentName);

    String res = "";
    Set<String> s = new TreeSet<>();
    for(String n : g.listNodes()){
      if(n.equals(p.getLabel())) {
        for (Edge<Double, String> e : g.getNode(n).getOutEdges()) {
          s.add(e.getDst().getLabel() + "(" + String.format("%.3f",e.getLabel()) + ")");
        }
      }
    }

    if(s.isEmpty()){
      res = " ";
    } else {
      Iterator<String> itr = s.iterator();
      while(itr.hasNext()){
        res += " "+itr.next();
      }
    }
    output.println("the children of " + parentName + " in " + graphName + " are:" + res);
  }

  public void findPath(List<String> arguments) {
    if (arguments.size() != 3) {
      throw new CommandException("Bad arguments to findPath: " + arguments);
    }
    String graphName = arguments.get(0);
    String node1 = arguments.get(1);
    String nodeN = arguments.get(2);
    findPath(graphName, node1, nodeN);
  }

  public void findPath(String graphName, String start, String end) {
    Graph<String, Double> graph = graphs.get(graphName);
    output.println("path from " + start + " to " + end + ":");

    if (!graph.listNodes().contains(start) && !graph.listNodes().contains(end)){
      output.println("unknown node " + start);
      output.println("unknown node " + end);
    } else if(!graph.listNodes().contains(start)){
      output.println("unknown node " + start);
    } else if (!graph.listNodes().contains(end)) {
      output.println("unknown node " + end);
    } else {
      Path<String> path = Dijkstra.findDijkstra(graph, start, end);

      if (path == null) {
        output.println("no path found");
      } else {
        for (Path.Segment p : path) {
          output.println(p.getStart() + " to " + p.getEnd() + " with weight " + String.format("%.3f", p.getCost()));
        }
        String fin = String.format("%.3f", path.getCost());
        output.println("total cost: " + fin);
      }
    }
  }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

      public CommandException() {
        super();
      }

      public CommandException(String s) {
        super(s);
      }

      public static final long serialVersionUID = 3495;
    }
  }

