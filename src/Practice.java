import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate
   * line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via
   * multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */

  // Danny
  public <T> void printVertexVals(Vertex<T> vertex) {
    printVertexValsHelper(vertex, new HashSet<>());
  }

  public static <T> void printVertexValsHelper(Vertex<T> vertex, Set<Vertex<T>> set) {
    if (vertex == null) {
      return;
    }

    if (set.contains(vertex)) {
      return;
    }

    set.add(vertex);
    System.out.println(vertex.data);

    for (var neighbor : vertex.neighbors) {
      printVertexValsHelper(neighbor, set);
    }

  }

  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is
   *         null.
   */
  // Jameson
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {

    return reachable(vertex, new HashSet<Vertex<T>>());
  }

  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null)
      return visited;
    if (visited.contains(vertex))
      return visited;

    visited.add(vertex);

    for (var neighbor : vertex.neighbors) {
      reachable(neighbor, visited);
    }
    return visited;

  }

  /**
   * Returns the maximum value among all vertices reachable from the given
   * starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if
   *         vertex is null.
   */
  // Danny
  public int max(Vertex<Integer> vertex) {
    return maxHelper(vertex, new HashSet<>());
  }

  public int maxHelper(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    int max = Integer.MIN_VALUE;

    if (vertex == null) {
      return max;
    }

    if (visited.contains(vertex)) {
      return max;
    }

    visited.add(vertex);

    for (var neighbor : vertex.neighbors) {
      if (neighbor.data > max) {
        max = neighbor.data;
      }

      int neighbordMax = maxHelper(neighbor, visited);

      if (neighbordMax > max) {
        max = neighbordMax;
      }
    }

    return max;
  }

  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if
   *         vertex is null.
   */
  // Jameson
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    return leaves(vertex, new HashSet<Vertex<T>>(), new HashSet<Vertex<T>>());
  }

  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> leaves) {
    if (vertex == null)
      return leaves;
    if (visited.contains(vertex))
      return leaves;
    visited.add(vertex);
    if (vertex.neighbors.isEmpty()) {
      leaves.add(vertex);
    }
    for (var neighbor : vertex.neighbors) {
      leaves(neighbor, visited, leaves);
    }
    return leaves;
  }

  /**
   * Determines whether there exists a strictly increasing path from the given
   * start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly
   * greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end   The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) {
    return hasStrictlyIncreasingPathHelper(start, end, new HashSet<Vertex<Integer>>());
  }

  public boolean hasStrictlyIncreasingPathHelper(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> visited){

    if (start == null || end == null){
      throw new NullPointerException("Start or End cannot be Null");
    }

    // if start and end are equal, the path is not increasing
    if(start.equals(end)){
      return true;
    }

    //add start to set to mark as visisted
    visited.add(start);

    for(var neighbor : start.neighbors){
      // only check neighbor data if not in visited and the data is greater than the current vertex
      if(!visited.contains(neighbor) && neighbor.data > start.data){
        if(hasStrictlyIncreasingPathHelper(neighbor, end, visited)){
          return true;
        }
      }
    }
    //remove from set to explore all other possibilites
    visited.remove(start);

    // no increasing path found
    return false;

  }
}
