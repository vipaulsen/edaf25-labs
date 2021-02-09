package lab3;

import graph.Graph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordGraph implements Graph<String> {
  private final Map<String, Set<String>> graph = new HashMap<>();

  public WordGraph(Path wordfile, WordCriteria criteria) throws IOException {
    try (Reader in = Files.newBufferedReader(wordfile);
    Scanner scan = new Scanner(in)) {
      while (scan.hasNext()) {
        String word = scan.nextLine();
        graph.put(word, new HashSet<String>());
      }
      for(String key : graph.keySet()){
        for(String s : graph.keySet()){
          if(criteria.adjacent(key,s)){
            graph.get(key).add(s);
          }
        }
      }
    }
  }

  @Override public int vertexCount() {
    return graph.size();
  }

  @Override public Collection<String> vertexSet() {
    return graph.keySet();
  }

  @Override public Collection<String> neighbours(String v) {
    return graph.get(v);
  }
}
