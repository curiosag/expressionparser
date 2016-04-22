# ExpressionParser

The odd expression parser using parser combinators using a generic evaluator. 3 evaluators are implementated for

* [Tracing]() and simple string representation
* Abstract syntax tree [AstEvaluator]()
* [DOT graph description](http://www.graphviz.org/Documentation.php) file for visualization

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
