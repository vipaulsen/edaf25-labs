package lab4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/** Helper class for the priority queue in Dijkstras algorithm. */
class PQElement {
  int node;
  int distance;

  public PQElement(int node, int dist) {
    this.node = node;
    this.distance = dist;
  }
}

public class Lab4 {
  /**
   * Computes the shortest distance between start and end in the graph g using
   * Dijkstra's algorithm. This version handles only graphs with integer edge
   * distances.
   * 
   * @param g     a graph with distance information attached to the edges
   * @param start start vertex
   * @param end   end vertex
   * @return shortest distance between start and end
   */
  public static int distance(DistanceGraph g, int start, int end) {

    // One map to keep track of visited nodes, one to keep track of distances from
    // the startnode to all other nodes
    Set<Integer> visited = new HashSet<>();
    Map<Integer, Integer> distancesFromStart = new HashMap<>();

    // En Comparator skapas för att hålla listan med bågar sorterad:
    Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
    PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);

    // Start has the distance 0 from start
    queue.add(new PQElement(start, 0));
    distancesFromStart.put(start, 0);
    visited.add(start);

    while (!queue.isEmpty()) {
      // elem = the next node to check from, starting with the node start, more nodes
      // gets added in the last if-statement
      PQElement currentElem = queue.poll();

      if (currentElem.node == end) {
        return distancesFromStart.get(currentElem.node);
      } else {
        for (Edge e : g.edges(currentElem.node)) {
          int theEdgeDestination = e.destination;

          int newDist = distancesFromStart.get(currentElem.node) + e.distance;
          int wDist = distancesFromStart.getOrDefault(theEdgeDestination, Integer.MAX_VALUE);

          if (!visited.contains(theEdgeDestination) || newDist < wDist) {
            queue.add(new PQElement(theEdgeDestination, newDist));
            distancesFromStart.put(theEdgeDestination, newDist);
            visited.add(theEdgeDestination);
          }
        }
      }
    }
    return -1;
  }

  /**
   * Finds a shortest path between start and end in a graph g Dijkstra's
   * algorithm. The graph can have any distance unit.
   * 
   * @param g     a graph with distance information attached to the edges
   * @param start start vertex
   * @param end   end vertex
   * @return a list containing the nodes on the shortest path from start to end
   */
  public static List<Integer> shortestPath(DistanceGraph g, int start, int end) {
    // One map to keep track of visited nodes, one to keep track of distances from
    // the startnode to all other nodes
    Set<Integer> visited = new HashSet<>();
    Map<Integer, Integer> distancesFromStart = new HashMap<>();
    Map<Integer, Integer> previousNode = new HashMap<>();
    LinkedList<Integer> answer = new LinkedList<>();

    // En Comparator skapas för att hålla listan med bågar sorterad:
    Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
    PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
    
    // Start has the distance 0 from start
    queue.add(new PQElement(start, 0));
    distancesFromStart.put(start, 0);
    visited.add(start);

    while (!queue.isEmpty()) {
      // elem = the next node to check from, starting with the node start, more nodes
      // gets added in the last if-statement
      PQElement currentElem = queue.poll();
      if (currentElem.node == end) {
        int currentNode = end;
        while(currentNode != start){
          answer.addFirst(currentNode);
          currentNode = previousNode.get(currentNode);
        }
        answer.addFirst(currentNode);
        return answer;
      } else {
        for (Edge e : g.edges(currentElem.node)) {
          int theEdgeDestination = e.destination;

          int newDist = distancesFromStart.get(currentElem.node) + e.distance;
          int wDist = distancesFromStart.getOrDefault(theEdgeDestination, Integer.MAX_VALUE);
          
          if (!visited.contains(theEdgeDestination) && newDist < wDist) {
            queue.add(new PQElement(theEdgeDestination, newDist));
            distancesFromStart.put(theEdgeDestination, newDist);
            previousNode.put(theEdgeDestination, currentElem.node);
            
          }
        }
      }
      visited.add(currentElem.node);
    }
    return new LinkedList<>();
  }
}
