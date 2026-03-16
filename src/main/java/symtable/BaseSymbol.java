package symtable;

public class BaseSymbol implements Symbol {
  private final String name;

  public BaseSymbol(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
