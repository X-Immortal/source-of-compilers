package codegen;

import org.antlr.v4.runtime.Token;

import java.io.FileWriter;
import java.io.IOException;

// String: temp variables
public class CodeGenVisitor extends ControlBaseVisitor<String> {
  private final FileWriter fileWriter;
  private int tempCounter = 1;
  private int labelCounter = 1;

  public CodeGenVisitor(String file) throws IOException {
    fileWriter = new FileWriter(file);
  }

  @Override
  public String visitProg(ControlParser.ProgContext ctx) {
    visit(ctx.stat());

    try {
      fileWriter.close();
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }

    return null;
  }

  // expr -> ID
  @Override
  public String visitIdExpr(ControlParser.IdExprContext ctx) {
    return ctx.getText();
  }

  // stat -> ID = expr
  @Override
  public String visitAssignStat(ControlParser.AssignStatContext ctx) {
    String expr = visit(ctx.expr());
    emitCode(ctx.ID().getText() + " = " + expr);

    return null;
  }

  // bool -> true
  @Override
  public String visitTrueExpr(ControlParser.TrueExprContext ctx) {
    return ctx.getText();
  }

  // bool -> false
  @Override
  public String visitFalseExpr(ControlParser.FalseExprContext ctx) {
    return ctx.getText();
  }

  // bool -> ! bool
  @Override
  public String visitNotExpr(ControlParser.NotExprContext ctx) {
    String bool = visit(ctx.bool());
    String temp = getNewTemp();
    emitCode(temp + " = " + " NOT " + bool);
    return temp;
  }

  // bool -> lhs = bool && rhs = bool
  @Override
  public String visitAndExpr(ControlParser.AndExprContext ctx) {
    String lhs = visit(ctx.lhs);
    String rhs = visit(ctx.rhs);

    String temp = getNewTemp();
    emitCode(temp + " = " + " AND " + lhs + " " + rhs);

    return temp;
  }

  // bool -> lhs = bool || rhs = bool
  @Override
  public String visitOrExpr(ControlParser.OrExprContext ctx) {
    String lhs = visit(ctx.lhs);
    String rhs = visit(ctx.rhs);

    String temp = getNewTemp();
    emitCode(temp + " = " + " OR " + lhs + " " + rhs);

    return temp;
  }

  // bool -> lhs = expr REL rhs = expr
  @Override
  public String visitRelExpr(ControlParser.RelExprContext ctx) {
    String lhs = visit(ctx.lhs);
    String rhs = visit(ctx.rhs);

    Token op = ctx.op;
    String cond = switch (op.getType()) {
      case ControlLexer.GT -> "sgt";   // signed greater than
      case ControlLexer.GE -> "sge";   // signed greater than or equal
      case ControlLexer.EE -> "eq";
      default -> throw new IllegalArgumentException("No such cond: " + op.getText());
    };

    String temp = getNewTemp();
    emitCode(temp + " = " + " icmp " + cond + " " + lhs + " " + rhs);

    return temp;
  }

  // stat -> first = stat second = stat
  @Override
  public String visitSeqStat(ControlParser.SeqStatContext ctx) {
    return super.visitSeqStat(ctx);
  }

  // stat -> if (bool) { stat }
  @Override
  public String visitIfStat(ControlParser.IfStatContext ctx) {
    return super.visitIfStat(ctx);
  }

  // stat -> if (bool) { ifStat = stat } else { elseStat = stat }
  @Override
  public String visitIfElseStat(ControlParser.IfElseStatContext ctx) {
    return super.visitIfElseStat(ctx);
  }

  // stat -> while (bool) { stat }
  @Override
  public String visitWhileStat(ControlParser.WhileStatContext ctx) {
    return super.visitWhileStat(ctx);
  }

  // stat -> break
  @Override
  public String visitBreakStat(ControlParser.BreakStatContext ctx) {
    return super.visitBreakStat(ctx);
  }

  private void emitLabel(String label) {
    try {
      fileWriter.write(label + ":\n");
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private void emitCode(String code) {
    try {
      fileWriter.write(code + '\n');
    } catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
  }

  private String getNewTemp() {
    return "t" + tempCounter++;
  }

  private String getNewLabel(String label) {
    return label + labelCounter++;
  }
}
