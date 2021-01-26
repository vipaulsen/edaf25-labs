package lab2;

import graph.Graph;

//import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Lab2 {

  /**
   * Generic depth first search in a graph, starting from the vertex u.
   *
   * @param g       the graph to search in
   * @param u       the start vertex
   * @param visited set of visited vertices (should be empty for a full search)
   * @param <T>     the vertex type
   */
  private static <T> void dfs(Graph<T> g, T u, Set<T> visited) {
    visited.add(u);
    for (T v : g.neighbours(u)) {
      if (!visited.contains(v)) {
        dfs(g, v, visited);
      }
    }
  }

  public static boolean isConnected(Graph<Integer> g) {
    Set<Integer> visited = new HashSet<>();
    dfs(g, 0, visited);

    if (visited.size() == g.vertexCount())
      return true;

    return false;
  }

  public static int nbrOfComponents(Graph<Integer> g) {
    Set<Integer> visited = new HashSet<>();
    int nbrOfComponents = 0;
    for (int i = 0; i < g.vertexCount(); i++) {
      if (!visited.contains(i)) {
        dfs(g, i, visited);
        nbrOfComponents++;
      }
    }

    return nbrOfComponents;
  }

  public static boolean pathExists(Graph<Integer> g, int u, int v) {
    Set<Integer> visited = new HashSet<>();
    return pathExists(g, u, v, visited);
  }

  private static <T> boolean pathExists(Graph<T> g, T u, T v, Set<T> visited) {
    if (u.equals(v))
      return true;
    visited.add(u);
    for (T t : g.neighbours(u)) {
      if (!visited.contains(t)) {
        if (pathExists(g, t, v, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  public static List<Integer> findPath(Graph<Integer> g, int u, int v) {
    Set<Integer> visited = new HashSet<>();
    List<Integer> path = new LinkedList<>();

    findPath(g, u, v, visited, path);

    System.out.println(path.toString());

    return path;
  }

  private static <T> boolean findPath(Graph<T> g, T u, T v, Set<T> visited, List<T> path) {
    if (u.equals(v)) {
      path.add(u);
      return true;
    }
    visited.add(u);
    path.add(u);
    for (T t : g.neighbours(u)) {
      if (!visited.contains(t)) {
        if (findPath(g, t, v, visited, path)) {
          return true;
        }
      }
    }
    path.remove(u);
    return false;
  }
}
