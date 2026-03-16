package symtable;

import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.OrderedHashSet;

import java.util.Set;

public class SymbolTableTreeGraph {
  private final Set<String> nodes = new OrderedHashSet<>();
  private final MultiMap<String, String> edges = new MultiMap<>();

  public void addNode(String node) {
    nodes.add(node);
  }

  public void addEdge(String caller, String callee) {
    edges.map(caller, callee);
  }

  static public String toDot(Scope scope) {
    return null;
  }
}
