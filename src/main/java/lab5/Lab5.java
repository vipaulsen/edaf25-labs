package lab5;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Lab5 {
  /**
   * Computes the maximum flow for a flow network.
   * 
   * @param g a graph with edge capacities and a source and sink
   * @return shortest distance between start and end
   */
  public static int maxFlow(FlowGraph g, int source, int sink) {
    int[][] flowCapacity = new int[g.vertexCount()][g.vertexCount()];
    for (int i = 0; i < flowCapacity.length; i++) {
      for (int j = 0; j < flowCapacity.length; j++) {
        flowCapacity[i][j] = g.getCapacity(i, j);
      }
    }
    int flow = 0;
    int bottleNeck = Integer.MAX_VALUE;
    int[] pred = new int[g.vertexCount()];
    while (true) {
      if (!bfs(g.vertexCount(), source, sink, flowCapacity, pred)) {
        break;
      }
      bottleNeck = Integer.MAX_VALUE;
      for (int i = sink; i != source; i = pred[i]) {
        if (flowCapacity[pred[i]][i] < bottleNeck) {
          bottleNeck = flowCapacity[pred[i]][i];
        }
      }

      flow += bottleNeck;

      for (int i = sink; i > source; i = pred[i]) {
        flowCapacity[pred[i]][i] -= bottleNeck;
        flowCapacity[i][pred[i]] += bottleNeck;
      }
    }
    return flow;
  }

  /**
   * Helper method for maxFlow, bredth-first search
   * 
   * @param numVertex amount of vertexes in the graph
   * @param start     the source-node
   * @param end       the sink-node
   * @param residual  the flow capacity from [row] to [column]
   * @param pred      an array with predecessors.
   * @return true if there is a way between start and end that has a capacity > 0
   *         in the residual matrix, else false
   */
  private static boolean bfs(int numVertex, int start, int end, int[][] residual, int[] pred) {
    Set<Integer> visited = new HashSet<>();
    LinkedList<Integer> queue = new LinkedList<>();

    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();

      for (int anotherNode = 0; anotherNode < numVertex; anotherNode++) {
        if (!visited.contains(anotherNode) && residual[currentNode][anotherNode] > 0) {
          queue.add(anotherNode);
          pred[anotherNode] = currentNode;
          visited.add(anotherNode);
        }
      }
    }
    return visited.contains(end);
  }

  /**
   * Read a flowgraph from a file.
   */
  public static FlowGraph loadFlowGraph(Path path) throws IOException {
    Scanner scan = new Scanner(path);
    int vertexAmount = scan.nextInt();
    int edgesAmount = scan.nextInt();
    FlowEdge[] flowEdges = new FlowEdge[edgesAmount];
    int i = 0;

    while(scan.hasNext()){
      int u = scan.nextInt();
      int v = scan.nextInt();
      int capacity = scan.nextInt();
      if(capacity == -1){
        capacity = Integer.MAX_VALUE;
      }
      flowEdges[i] = new FlowEdge(u, v, capacity);
      i++;
    }

    scan.close();
    return new FlowGraph(vertexAmount, flowEdges);
  }
}
