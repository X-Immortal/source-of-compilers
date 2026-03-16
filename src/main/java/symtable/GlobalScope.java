package symtable;

public class GlobalScope extends BaseScope {
  public GlobalScope(Scope enclosingScope) {
    super("global", enclosingScope);
  }
}
