# ExpressionParser

The ordinary expression parser using parser combinators. The Scala black magic level is low, but it uses a generic evaluator class. Two evaluators are implementated for

* [Tracing](https://github.com/curiosag/expressionparser/blob/master/src/org/cg/scala/expressionparser/ExprEvaluator.scala) and a simple string representation of the parse result
* Abstract syntax tree creation [(AstEvaluator)](https://github.com/curiosag/expressionparser/blob/master/src/org/cg/scala/expressionparser/AstEvaluator.scala)

There's a converter to generate a [DOT graph description](http://www.graphviz.org/Documentation.php) file from the AST for visualization [(Ast2Dot)](https://github.com/curiosag/expressionparser/blob/master/src/org/cg/scala/expressionparser/Ast2Dot.scala)

By the way in another place there's an implementation for the evaluator doing actual [boolean evaluation](https://github.com/curiosag/AdScraperExpressionFilter/blob/master/src/org/cg/adscraper/exprFilter/ExprContextAdScraper.scala) on raw data in a web scraper environment. It deals with the possibility that evaluation may fail at any moment due to missing or flawed data and shows a pretty paranoid relationship to the parser as well.

That's covered:
    
    identifiers: x, y ...
    boolean literals: false, true
    numeric literals: 1, -1, 1.1 
    boolean expressions: ! false & true | (false | x)
    numeric comparisons and numeric intervals: ! 1 > 2 & 0 <= x < 100
    function call syntax, allowing only identifiers as params: passes(refvalue, filterid) & someFct(a, x, y)
    
Note that "&" and "|" have equal precedence as defined here

     <b-expression>::= <b-term> ["|"|& <b-term>]*
     <not-factor>  ::= [!] <b-factor>
     <b-factor>    ::= <booleanliteral> | <identifier> | numRelOp | numInterval | functionCall | (<b-expression>) 
     <functionCall>::= <identifier> (<identifier> [<listSep> <identifier>]*)
     <numRelOp> 	  ::= <numericTerminal> <relOp> <numericTerminal>
     <numInterval> ::= <numericTerminal> <relOp> <numericTerminal> <RelOp> <numericTerminal>
     <booleanliteral>   ::= true | false 
     <numericTerminal> ::= <identifier> | <numericLiteral>
     <relOp>				::= > | < | >= | <= | ==
     <listSep>			::= ,
