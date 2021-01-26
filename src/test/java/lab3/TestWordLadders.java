package lab3;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.google.common.truth.Truth.assert_;

public class TestWordLadders {

  @Test public void testWords1() throws IOException {
    WordGraph graph = loadGraph("words-1.txt");
    assertPath(graph, "varm", "kall", 4);
  }

  @Test public void testWords2() throws IOException {
    WordGraph graph = loadGraph("words-2.txt");
    assertPath(graph, "green", "greet", 1);
    assertPath(graph, "greet", "great", 1);
    assertPath(graph, "great", "groan", 2);
    assertPath(graph, "groan", "brown", 2);
    assertPath(graph, "green", "brown", 6);
  }

  @Test public void testWords3() throws IOException {
    WordGraph graph = loadGraph("words-3.txt");
    assertPath(graph, "green", "greet", 1);
    assertPath(graph, "greet", "great", 1);
    assertPath(graph, "great", "groan", 2);
    assertPath(graph, "groan", "brown", 2);
    assertPath(graph, "green", "brown", 6);
    assertPath(graph, "oared", "bared", 1);
    assertPath(graph, "oared", "oared", 0);
    assertPath(graph, "daily", "hones", 6);
    assertPath(graph, "meter", "micro", -1);
    assertPath(graph, "prawn", "bread", 9);
    assertPath(graph, "zombi", "aargh", -1);
    assertPath(graph, "aargh", "zombi", -1);
    assertPath(graph, "alpha", "gamma", -1);
    assertPath(graph, "bring", "beers", 7);
    assertPath(graph, "altos", "duets", -1);
    assertPath(graph, "abaca", "papas", 13);
  }

  private WordGraph loadGraph(String testcase) throws IOException {
    Path wordfile = Paths.get(testcase);
    return new WordGraph(wordfile, new OneLetterDiff());
  }

  private void assertPath(WordGraph graph, String word1, String word2, int expectedDist) {
    int distance = Lab3.distance(graph, word1, word2);
    assert_()
        .withMessage(String.format("wrong distance from '%s' to '%s'", word1, word2))
        .that(distance)
        .isEqualTo(expectedDist);
  }

}
