/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package defaultvisitor.demo.b_originalvisitorwithminus;

public class VisitorTest {
  /*
  @Test
  public void onePlusTwoPlusThreePlusFourCounting() {
    Expression expression = new Plus(new Number(1), new Plus(new Number(2), new Plus(new Number(3), new Number(4))));
    NumberCountVisitor counter = new NumberCountVisitor();
    expression.accept(counter);
    assertEquals(4, counter.getCount());
  }

  @Test
  public void onePlusTwoMinusThreeMinusFourCounting() {
    Expression expression = new Plus(new Number(1), new Minus(new Number(2), new Minus(new Number(3), new Number(4))));
    NumberCountVisitor counter = new NumberCountVisitor();
    expression.accept(counter);
    assertEquals(4, counter.getCount());
  }

  @Test
  public void onePlusTwoMinusThreeMinusFourReorderCounting() {
    Expression expression = new Minus(new Plus(new Number(1), new Number(2)), new Minus(new Number(3), new Number(4)));
    NumberCountVisitor counter = new NumberCountVisitor();
    expression.accept(counter);
    assertEquals(4, counter.getCount());
  }

  @Test
  public void onePlusTwoMinusThreeMinusFourReorder2Counting() {
    Expression expression = new Minus(new Minus(new Plus(new Number(1), new Number(2)), new Number(3)), new Number(4));
    NumberCountVisitor counter = new NumberCountVisitor();
    expression.accept(counter);
    assertEquals(4, counter.getCount());
  }


  @Test
  public void onePlusTwoPlusThreePlusFourPrinting() {
    Expression expression = new Plus(new Number(1), new Plus(new Number(2), new Plus(new Number(3), new Number(4))));
    PrintVisitor print = new PrintVisitor();
    expression.accept(print);
    assertEquals("1 2 3 4 + + +", print.toString());
  }


  @Test
  public void onePlusTwoMinusThreeMinusFourPrinting() {
    Expression expression = new Plus(new Number(1), new Minus(new Number(2), new Minus(new Number(3), new Number(4))));
    PrintVisitor print = new PrintVisitor();
    expression.accept(print);
    assertEquals("1 2 3 4 - - +", print.toString());
  }

  @Test
  public void onePlusTwoMinusThreeMinusFourReorderPrinting() {
    Expression expression = new Minus(new Plus(new Number(1), new Number(2)), new Minus(new Number(3), new Number(4)));
    PrintVisitor print = new PrintVisitor();
    expression.accept(print);
    assertEquals("1 2 + 3 4 - -", print.toString());
  }

  @Test
  public void pnePlusTwoMinusThreeMinusFourReorder2Printing() {
    Expression expression = new Minus(new Minus(new Plus(new Number(1), new Number(2)), new Number(3)), new Number(4));
    PrintVisitor print = new PrintVisitor();
    expression.accept(print);
    assertEquals("1 2 + 3 - 4 -", print.toString());
  }

  @Test
  public void onePlusTwoPlusThreePlusFourEval() {
    Expression expression = new Plus(new Number(1), new Plus(new Number(2), new Plus(new Number(3), new Number(4))));
    EvalVisitor eval = new EvalVisitor();
    expression.accept(eval);
    assertEquals(10, eval.getValue());
  }


  @Test
  public void onePlusTwoMinusThreeMinusFourEval() {
    Expression expression = new Plus(new Number(1), new Minus(new Number(2), new Minus(new Number(3), new Number(4))));
    EvalVisitor eval = new EvalVisitor();
    expression.accept(eval);
    assertEquals(4, eval.getValue());
  }

  @Test
  public void onePlusTwoMinusThreeMinusFourReorderEval() {
    Expression expression = new Minus(new Plus(new Number(1), new Number(2)), new Minus(new Number(3), new Number(4)));
    EvalVisitor eval = new EvalVisitor();
    expression.accept(eval);
    assertEquals(4, eval.getValue());
  }

  @Test
  public void onePlusTwoMinusThreeMinusFourReorder2Eval() {
    Expression expression = new Minus(new Minus(new Plus(new Number(1), new Number(2)), new Number(3)), new Number(4));
    EvalVisitor eval = new EvalVisitor();
    expression.accept(eval);
    assertEquals(-4, eval.getValue());
  }
  //*/
}
