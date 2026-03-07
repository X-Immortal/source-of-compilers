lexer grammar DragonLexerRules;

@header {
package dragon;
}

DOT : '.' ;
POS : '+' ;
NEG : '-' ;

EQ : '=' ;
NE : '<>' ;
LT : '<' ;
LE : '<=' ;
GT : '>' ;
GE : '>=' ;

IF : 'if' ;
ELSE : 'else' ;
ID : LETTER (LETTER | DIGIT)* ;

// pay more attention to this group
INT : DIGITS ;
// here "2." is an invalid REAL
REAL : DIGITS ('.' DIGITS)? ;
SCI : DIGITS ('.' DIGITS)? ([eE] [+-]? DIGITS)? ;

WS : [ \t\r\n]+ -> skip ;

fragment DIGIT : [0-9] ;
fragment DIGITS : [0-9]+ ;
fragment LETTER : [a-zA-Z] ;