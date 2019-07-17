package marvel;
import graph.*;

import java.util.LinkedList;
import java.util.*;

public class MarvelPaths{
    public static void main(String[] args){
        Graph<String, String> graph = MarvelPaths.buildGraph("src/test/resources/marvel/data/marvel.tsv");

        Scanner console = new Scanner(System.in);
        System.out.println("Are you curious?");
        String ans = console.nextLine();
        if(ans.equals("yes")) {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*<<WELCOME TO THE MARVEL UNIVERSE>>=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println();
            System.out.println("You have now entered a top secret system that allows you to access the connections between any heros in the Marvel Universe");
            System.out.println("Proceed with caution and have fun.");
            System.out.println();
            System.out.println();
            System.out.println("Pick first hero: ");
            String first = console.nextLine();
            System.out.println("Pick second hero: ");
            System.out.println();
            String second = console.nextLine();
            List<Node<String, String>> path = MarvelPaths.findPath(graph, first, second);

            if (!graph.listNodes().contains(new Node<String, String>(first))) {
                System.out.println("********************<WARNING>********************");
                System.out.println("I'm sorry, " + first + " is not a valid hero");
            } else if (!graph.listNodes().contains(new Node<String, String>(second))) {
                System.out.println("********************<WARNING>********************");
                System.out.println("I'm sorry, " + second + " is not a valid hero");
            } else if (path.isEmpty() && !first.equals(second)) {
                System.out.println("Path from " + first + " to " + second + ":");
                System.out.println("path not found");
            } else {
                System.out.println("Loading...");
                for (int i = 0; i < path.size() - 1; i++) {
                    System.out.println("From " + path.get(i) + " to " + path.get(i + 1) + " via " + graph.findConnection(path.get(i), path.get(i)).get(0));
                }
            }
        } else {
            System.out.println("Goodbye....");
        }
    }

    public static List<Node<String, String>> findPath(Graph<String, String> graph, String start, String end){
        Node<String, String> begin = graph.getNode(start);
        Node<String, String> finish = graph.getNode(end);

        Queue<Node<String, String>> worklist = new LinkedList<>();
        Map<Node<String, String>, List<Node<String, String>>> pathToNode = new HashMap<>();

        pathToNode.put(begin, new LinkedList<>());
        worklist.add(begin);

        while(!worklist.isEmpty()){
            Node<String, String> next = worklist.remove();
            if(next.equals(finish)){
                return pathToNode.get(next);
            }
            //List<Node<String, String>> nodes = next.getChildren();

            for(Node<String, String> n : graph.getNode(next.getLabel()).getChildren()){
                if(!pathToNode.containsKey(n)){
                    List<Node<String, String>> path = new LinkedList<>(pathToNode.get(next));
                    path.add(n);
                    pathToNode.put(n, path);
                    worklist.add(n);
                }
            }
        }
        return pathToNode.get(begin);
    }

    public static Graph<String, String> buildGraph(String filename){
        Map<String, List<String>> m = new HashMap<>(MarvelParser.parseData(filename));
        //m.putAll(MarvelParser.parseData(filename));
        Graph<String, String> marvelGraph = new Graph<>();

        for(List<String> lists : m.values()){
            for(String hero : lists) {
                marvelGraph.addNode(hero);
            }
        }

        for(String book : m.keySet()){
            for(String hero1 : m.get(book)){
                for(String hero2 : m.get(book)){
//                    Node<String, String> n = new Node<>(hero1);
//                    Node<String, String> n2 = new Node<>(hero2);
                    if(!hero1.equals(hero2)) {
                        Edge<String, String> e = new Edge<>(book, marvelGraph.getNode(hero1), marvelGraph.getNode(hero2));
                        marvelGraph.addEdge(e);
                    }
                }
            }
        }

        return marvelGraph;
    }
}
