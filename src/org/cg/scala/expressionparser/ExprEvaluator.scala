package org.cg.scala.expressionparser

trait ExprEvaluator[T] {
  def evalConst(const: Id): EvalResult[T]
  def evalUnOp(arg: EvalResult[T], op: Op): EvalResult[T]
  def evalRelOp(val1: Token, op: Op, val2: Token): EvalResult[T]
  def evalBinOpBoolean(arg1: EvalResult[T], op: Op, arg2: EvalResult[T]): EvalResult[T]
  def evalFunc(name: Id, params: List[Token]): EvalResult[T]
}

object tracingEvaluator extends ExprEvaluator[String] {
  def trace(v: String) = {System.out.println(v)}

  override def evalConst(const: Id): EvalResult[String] = {
    trace("evalConst: " + const.token)
    EvalOk(const.token)
  }

  override def evalUnOp(arg: EvalResult[String], op: Op): EvalResult[String] = {
    val ret = "(%s %s)".format(op.token, arg)
    trace("evalUnOp: " + ret)
    EvalOk(ret)
  }

  override def evalRelOp(val1: Token, op: Op, val2: Token): EvalResult[String] = {
    val ret = "%s %s %s".format(val1.token, op.token, val2.token)
    trace("evalRelOp: " + ret)
    EvalOk(ret)
  }

  override def evalBinOpBoolean(arg1: EvalResult[String], op: Op, arg2: EvalResult[String]): EvalResult[String] = {
    val ret = "(%s %s %s)".format(arg1, op.token, arg2)
    trace("evalBinOpBoolean: " + ret)
    EvalOk(ret)
  }

  override def evalFunc(name: Id, params: List[Token]): EvalResult[String] = {
    val ret = "passFilter: %s params: %s".format(name.token, params.foldLeft("")((x, y) => x + " " + y.token))
    trace("evalPassFilter: " + ret)
    EvalOk(ret)
  }
}