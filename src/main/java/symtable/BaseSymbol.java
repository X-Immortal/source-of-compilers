package symtable;

public class BaseSymbol implements Symbol {
  final String name;
  final Type type;

  public BaseSymbol(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public Type getType() {
    return type;
  }

  @Override
  public String toString() {
    return "BaseSymbol{" +
        "name='" + name + '\'' +
        ", type=" + type +
        '}';
  }
}
