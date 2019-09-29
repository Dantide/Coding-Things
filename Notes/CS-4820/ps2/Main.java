import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // BufferedReader reader = new BufferedReader(
    // new
    // FileReader("C:/Users/ikmuj/Documents/Programming/4820/ps2/tests/input1.txt"));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    try {
      // Read input
      int numNodes = Integer.valueOf(reader.readLine());
      int numEdges = Integer.valueOf(reader.readLine());

      // Initialize variables
      int numEdgesToAdd = numNodes - 3;
      UnionFind unionFind = new UnionFind(numNodes);
      PriorityQueue<Edge> queue;
      TreeSet<Edge> tree = new TreeSet<Edge>(Edge.EDGE_COMPARATOR);
      HashSet<SimpleSet> metaSet = new HashSet<SimpleSet>();

      // Get Edge List/Queue
      for (int i = 0; i < numEdges; i++) {
        String[] edgeInfo = reader.readLine().split(" ");
        tree.add(new Edge(Integer.valueOf(edgeInfo[0]), Integer.valueOf(edgeInfo[1]), Integer.valueOf(edgeInfo[2])));
      }
      queue = new PriorityQueue<Edge>(tree);

      // Add Edges
      while (numEdgesToAdd > 0) {
        Edge edge = queue.poll();
        SimpleSet set1 = unionFind.Find(edge.node1);
        SimpleSet set2 = unionFind.Find(edge.node2);
        if (set1 != unionFind.Find(edge.node2)) {
          if (metaSet.contains(set2)) {
            metaSet.remove(set2);
          }
          metaSet.add(unionFind.Union(set1.name, set2.name));
          numEdgesToAdd -= 1;
        }
      }

      // Print out Answer
      for (int i = 0; i < (3 - metaSet.size()); i++) {
        writer.write('1');
        writer.newLine();
      }
      ArrayList<SimpleSet> temp = new ArrayList<SimpleSet>(metaSet);
      temp.sort(SimpleSet.SIMPLESET_COMPARATOR);
      for (SimpleSet s : temp) {
        writer.write(s.size + "", 0, (s.size + "").length());
        writer.newLine();
      }
      writer.flush();

      // Close IOStreams
      reader.close();
      writer.close();
    } catch (IOException e) {
      System.out.println("Something went wrong with reading or writing.");
    }
  }
}

class UnionFind {
  private SimpleSet[] component;

  /**
   * All components are initially null.
   */
  public UnionFind(int numNodes) {
    component = new SimpleSet[numNodes];
  }

  /**
   * Implemented based off of the textbook, page 157.
   */
  public SimpleSet Find(int node) {
    if (component[node] == null) {
      return new SimpleSet(1, node);
    } else if (component[node].name == node) {
      return component[node];
    } else {
      return component[node] = Find(component[node].name);
    }
  }

  /**
   * Implemented based off the textbook, page 154.
   */
  public SimpleSet Union(int node1, int node2) {
    // Sets the second set as a pointer to the first.
    SimpleSet set1 = component[node1] == null ? component[node1] = new SimpleSet(1, node1) : component[node1];
    SimpleSet set2 = component[node2] == null ? component[node2] = new SimpleSet(1, node1) : component[node2];
    set2.name = set1.name;
    set1.updateSet(set1.size + set2.size);
    component[node2] = set1;
    return set1;
  }
}

class SimpleSet {
  public int size;
  public int name;

  public static final Comparator<SimpleSet> SIMPLESET_COMPARATOR = new Comparator<SimpleSet>() {
    public int compare(SimpleSet s1, SimpleSet s2) {
      return s1.size - s2.size;
    }
  };

  public SimpleSet(int value, int name) {
    this.size = value;
    this.name = name;
  }

  public void updateSet(int newValue) {
    this.size = newValue;
  }

  public int compareTo(SimpleSet s) {
    return (this.size - s.size);
  }
}

class Edge {
  public int node1;
  public int node2;
  public int cost;

  public static final Comparator<Edge> EDGE_COMPARATOR = new Comparator<Edge>() {
    public int compare(Edge e1, Edge e2) {
      return (e1.cost - e2.cost);
    }
  };

  public Edge(int node1, int node2, int cost) {
    this.node1 = node1;
    this.node2 = node2;
    this.cost = cost;
  }

  public int compareTo(Edge e) {
    return (this.cost - e.cost);
  }
}