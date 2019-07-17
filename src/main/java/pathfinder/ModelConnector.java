/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import java.util.*;
import pathfinder.datastructures.*;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.Map;
import graph.*;

/*
In the pathfinder homework, the text user interface calls these methods to talk
to your model. In the campuspaths homework, your graphical user interface
will ultimately make class to these methods (through a web server) to
talk to your model the same way.

This is the power of the Model-View-Controller pattern, two completely different
user interfaces can use the same model to display and interact with data in
different ways, without requiring a lot of work to change things over.
*/

/**
 * This class represents the connection between the view and controller and the model
 * for the pathfinder and campus paths applications.
 */
public class ModelConnector {

  /**
   * Creates a new {@link ModelConnector} and initializes it to contain data about
   * pathways and buildings or locations of interest on the campus of the University
   * of Washington, Seattle. When this constructor completes, the dataset is loaded
   * and prepared, and any method may be called on this object to query the data.
   */

  private static List<CampusBuilding> buildings;
  private static List<CampusPath> paths;
  Graph<Point, Double> graph = new Graph<>();


  public ModelConnector() {
    buildings = CampusPathsParser.parseCampusBuildings();
    paths = CampusPathsParser.parseCampusPaths();

    for(CampusPath p : paths){
      Point pt1 = new Point(p.getX1(), p.getY1());
      Point pt2 = new Point(p.getX2(), p.getY2());
      graph.addNode(pt1);
      graph.addNode(pt2);

    }
    for(CampusPath p : paths){
      Edge<Double, Point> e = new Edge<>(p.getDistance(), graph.getNode(new Point(p.getX1(), p.getY1())), graph.getNode(new Point(p.getX2(), p.getY2())));
      graph.addEdge(e);
    }
//    for(CampusPath p : paths){
//
//    }
//
//    Node<Point, Double> n1 = new Node<>(pt1);
//    Node<Point, Double> n2 = new Node<>(pt1);
//    Edge<Double, Point> e = new Edge<>(p.getDistance(), n1, n2);
//    graph.addEdge(e);

    // TODO: You'll want to do things like read in the campus data and assemble your graph.
    // Remember the tenets of design that you've learned. You shouldn't necessarily do everything
    // you need for the model in this one constructor, factor code out to helper methods or
    // classes to work with your design best. The only thing that needs to remain the
    // same is the name of this class and the four method signatures below, because the
    // Pathfinder application calls these methods in order to talk to your model.
    // Change and add anything else as you'd like.
  }

  /**
   * @param shortName The short name of a building to query.
   * @return {@literal true} iff the short name provided exists in this campus map.
   */
  public boolean shortNameExists(String shortName) {
    // TODO: Implement this method to talk to your model, then remove the exception below.
    //if(shortNameExists(shortName)){
      //return buildingNames().containsKey(shortName);
    //}
    for(CampusBuilding b : buildings){
      if(b.getShortName().equals(shortName)){
        return true;
      }
    }
    return false;
  }

  /**
   * @param shortName The short name of a building to look up.
   * @return The long name of the building corresponding to the provided short name.
   * @throws IllegalArgumentException if the short name provided does not exist.
   */
  public String longNameForShort(String shortName) {
    // TODO: Implement this method to talk to your model, then remove the exception below.
    if(!shortNameExists(shortName)){
      throw new IllegalArgumentException("short name does not exist");
    }
    for(CampusBuilding b : buildings){
      if(b.getShortName().equals(shortName)){
        return b.getLongName();
      }
    }
    return null;
  }

  /**
   * @return The mapping from all the buildings' short names to their long names in this campus map.
   */
  public Map<String, String> buildingNames() {
    // TODO: Implement this method to talk to your model, then remove the exception below.
    Map<String, String> names = new HashMap<>();
    for(CampusBuilding b : buildings){
      if(shortNameExists(b.getShortName()) && longNameForShort(b.getShortName()) != null) {
        names.put(b.getShortName(), b.getLongName());
      }
    }
    return names;
  }

  /**
   * Finds the shortest path, by distance, between the two provided buildings.
   *
   * @param startShortName The short name of the building at the beginning of this path.
   * @param endShortName   The short name of the building at the end of this path.
   * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
   * if none exists.
   * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
   *                                  {@literal null}, or not valid short names of buildings in
   *                                  this campus map.
   */
  public Path<Point> findShortestPath(String startShortName, String endShortName) {
    // TODO: Implement this method to talk to your model, then remove the exception below.
    if(startShortName == null || endShortName == null || !shortNameExists(startShortName) || !shortNameExists(endShortName)){
      throw new IllegalArgumentException("not valid buildings");
    }

    CampusBuilding start = null;
    CampusBuilding end = null;
    for(CampusBuilding b : buildings){
      if(b.getShortName().equals(startShortName)){
        start = b;
      } else if(b.getShortName().equals(endShortName)){
        end = b;
      }
    }

    Point s = new Point(start.getX(), start.getY());
    Point e = new Point(end.getX(), end.getY());

    Path<Point> shortest = Dijkstra.findDijkstra(graph, s, e);
    return shortest;
  }

}
