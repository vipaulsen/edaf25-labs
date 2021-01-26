package lab3;

import graph.Graph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordGraph implements Graph<String> {
  private final Map<String, Set<String>> graph = new HashMap<>();

  public WordGraph(Path wordfile, WordCriteria criteria) throws IOException {
    // TODO(D1): load all words from file (wordfile).
    // TODO(D3): compute word neighbours (according to criteria).
  }

  @Override public int vertexCount() {
    // TODO(D1): implement this!
    return 0;
  }

  @Override public Collection<String> vertexSet() {
    // TODO(D1): implement this!
    return Collections.emptySet();
  }

  @Override public Collection<String> neighbours(String v) {
    return graph.getOrDefault(v, Collections.emptySet());
  }
}
