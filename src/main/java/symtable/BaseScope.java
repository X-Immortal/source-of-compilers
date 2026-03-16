package symtable;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseScope implements Scope {
  private String name;
  private final Map<String, Symbol> symbols = new LinkedHashMap<>();
  private final Scope enclosingScope;

  public BaseScope(String name, Scope enclosingScope) {
    this.name = name;
    this.enclosingScope = enclosingScope;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Map<String, Symbol> getSymbols() {
    return this.symbols;
  }

  @Override
  public Scope getEnclosingScope() {
    return this.enclosingScope;
  }

  @Override
  public void define(Symbol symbol) {
    this.symbols.put(symbol.getName(), symbol);
    System.out.println("+" + symbol.getName());
  }

  @Override
  public Symbol resolve(String name) {
    Symbol symbol = this.symbols.get(name);
    if (symbol != null) {
      System.out.println("-" + name);
      return symbol;
    }

    if (this.enclosingScope != null) {
      return this.enclosingScope.resolve(name);
    }

    System.out.println("?" + name);
    return null;
  }

  @Override
  public String toString() {
    return "BaseScope{" +
        "symbols=" + symbols +
        ", name='" + name + '\'' +
        '}';
  }
}
