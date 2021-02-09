package lab3;

import graph.Graph;

import java.util.HashSet;
import java.util.Set;

public class Lab3 {
  public static <T> int distance(Graph<T> g, T source, T dest) {
    int distance = 0;

    Set<T> visited = new HashSet<T>();
    Set<T> curLevel = new HashSet<T>();
    visited.add(source);
    curLevel.add(source);

    while (!curLevel.isEmpty()) {
      Set<T> nextLevel = new HashSet<T>();
      for (T w : curLevel) {
        if (w.equals(dest)) {
          return distance;
        }
        for (T n : g.neighbours(w)) {
          if (!visited.contains(n)) {
            visited.add(n);
            nextLevel.add(n);
          }
        }
      }
      distance++;
      curLevel = nextLevel;
    }
    return -1;
  }
}
