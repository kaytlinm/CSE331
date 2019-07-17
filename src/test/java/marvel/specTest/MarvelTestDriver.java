package marvel.specTest;

import graph.Graph;
import graph.*;
import marvel.MarvelPaths;
//import graph.Node;

import java.io.*;
import java.util.*;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph.
 **/
public class MarvelTestDriver {

  public static void main(String args[]) {
    try {
      if (args.length > 1) {
        printUsage();
        return;
      }

      MarvelTestDriver td;

      if (args.length == 0) {
        td = new MarvelTestDriver(new InputStreamReader(System.in),
                new OutputStreamWriter(System.out));
      } else {

        String fileName = args[0];
        File tests = new File (fileName);

        if (tests.exists() || tests.canRead()) {
          td = new MarvelTestDriver(new FileReader(tests),
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

  /** String -> Graph: maps the names of graphs to the actual graph **/
  //TODO for the student: Parameterize the next line correctly.
  private final Map<String, Graph<String, String>> graphs = new HashMap<String, Graph<String, String>>();
  private final PrintWriter output;
  private final BufferedReader input;

  /**
   * @requires r != null && w != null
   *
   * @effects Creates a new GraphTestDriver which reads command from
   * <tt>r</tt> and writes results to <tt>w</tt>.
   **/
  public MarvelTestDriver(Reader r, Writer w) {
    input = new BufferedReader(r);
    output = new PrintWriter(w);
  }

  /**
   * @effects Executes the commands read from the input and writes results to the output
   * @throws IOException if the input or output sources encounter an IOException
   **/
  public void runTests()
          throws IOException
  {
    String inputLine;
    while ((inputLine = input.readLine()) != null) {
      if ((inputLine.trim().length() == 0) ||
              (inputLine.charAt(0) == '#')) {
        // echo blank and comment lines
        output.println(inputLine);
      }
      else
      {
        // separate the input line on white space
        StringTokenizer st = new StringTokenizer(inputLine);
        if (st.hasMoreTokens()) {
          String command = st.nextToken();

          List<String> arguments = new ArrayList<String>();
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
      } else if(command.equals("LoadGraph")) {
        LoadGraph(arguments);
      }else if(command.equals("FindPath")){
        findPath(arguments);
      }else {
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

    graphs.put(graphName, new Graph<String, String>());
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

    Graph<String, String> g = graphs.get(graphName);
    //Node<String, String> n = new Node<String, String>(nodeName);
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
    String edgeLabel = arguments.get(3);

    addEdge(graphName, parentName, childName, edgeLabel);
  }

  private void addEdge(String graphName, String parentName, String childName,
                       String edgeLabel) {
    // Insert your code here.

    Graph<String, String> g = graphs.get(graphName);
    if (parentName.equals(childName)) {
      Node<String, String> s = new Node<String, String>(parentName);
      Edge<String, String> e = new Edge<String, String>(edgeLabel, s, s);
      g.addEdge(e);
    } else if(!parentName.equals(childName)){
      Node<String, String> p = new Node<String, String>(parentName);
      Node<String, String> c = new Node<String, String>(childName);
      Edge<String, String> e = new Edge<String, String>(edgeLabel, p, c);
      g.addEdge(e);
    }
    output.println("added edge " + edgeLabel + " from " + parentName + " to " + childName + " in " + graphName);
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

    Graph<String, String> g = graphs.get(graphName);

    String res = "";
    Set<String> s = new HashSet<>();
    for(String n : g.listNodes()){
      s.add(n);
    }

    if(s.isEmpty()){
      res = " ";
    } else {
      Iterator<String> itr = s.iterator();
      while(itr.hasNext()){
        res += " "+itr.next();
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
    // Insert your code here.
    Graph<String, String> g = graphs.get(graphName);
    Node<String, String> p = new Node<>(parentName);

    String res = "";
    Set<String> s = new TreeSet<>();

//        for(Node<String, String> n : g.getNode(parentName).getChildren()){
    for(Edge<String, String> e : g.getNode(parentName).getOutEdges()){
      if(!e.getDst().getLabel().equals(parentName)) {
        s.add(e.getDst().getLabel() + "(" + e.getLabel() + ")");
      }
    }
//        }
//         for(Node<String, String> n : g.listNodes()){
//             if(n.getLabel().equals(p.getLabel())) {
//                 for (Edge<String, String> e : n.getOutEdges()) {
//                     s.add(e.getDst().getLabel() + "(" + e.getLabel() + ")");
//                 }
//             }
//         }

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

  public void LoadGraph(List<String> arguments){
    if(arguments.size() != 2){
      throw new CommandException("Bad arguments to buildGraph: " + arguments);
    }
    String graphName = arguments.get(0);
    String filename = "src/test/resources/marvel/data/" + arguments.get(1);
    LoadGraph(graphName, filename);
  }

  public void LoadGraph(String graphName, String filename){
      Graph<String, String> graph = MarvelPaths.buildGraph(filename);
      graphs.put(graphName, graph);
      output.println("loaded graph " + graphName);
  }

  public void findPath(List<String> arguments){
    if(arguments.size() != 3){
      throw new CommandException("Bad arguments to findPath: " + arguments);
    }
    String graphName = arguments.get(0);
    String node1 = arguments.get(1);
    String nodeN = arguments.get(2);
    findPath(graphName, node1, nodeN);
  }

  public void findPath(String graphName, String start, String end) {
    start = start.replace('_', ' ');
    end = end.replace('_', ' ');
    Graph<String, String> graph = graphs.get(graphName);
    List<Node<String, String>> path = MarvelPaths.findPath(graph, start, end);


    output.println("path from " + start + " to " + end + ":");
    if (path.isEmpty() || path == null) {
      output.println("no path found");
    }else if (!path.isEmpty()){
      Node<String, String> s = graph.getNode(start);

     //Comparator<Edge<String, String>> nodeL = Comparator.comparing(Node::getLabel);
      LinkedList<String> list = new LinkedList<>(graph.findConnection(s, path.get(0)));
      Collections.sort(list);

      //String connect = graph.findConnection(s,path.get(0));
      //System.out.println(graph.findConnection(s, list.get(list.size()-1)));
      output.println(start + " to " + path.get(0).getLabel() + " via " + list.get(0));

      for(Node<String, String> n : path){
        for(Edge<String, String> e : graph.getNode(n.getLabel()).getOutEdges()){
          if(e.getDst().getLabel().equals(end)){
            output.println(n.getLabel() + " to " + end + " via " + e.getLabel());
          } else if(path.contains(graph.getNode(e.getDst().getLabel())) && !e.getDst().getLabel().equals(end)){
            output.println(n.getLabel() + " to " + e.getDst().getLabel() + " via " + e.getLabel());
          }
        }
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
