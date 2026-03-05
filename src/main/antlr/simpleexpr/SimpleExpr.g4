grammar SimpleExpr; // 语言名与文件名保持一致

@header {
package simpleexpr;
}

prog : stat* EOF ;

stat : expr ';'
     | ID '=' expr ';' // 备选分支
     | 'print' expr ';'
     ;

expr : expr ('*' | '/') expr // 递归
     | expr ('+' | '-') expr
     | '(' expr ')'
     | ID
     | INT
     | FLOAT
     ;

// 语法单元规约的名字全小写，词法单元规约的名字全大写

SEMI : ';' ;
ASSIGN : '=' ;
PRINT : 'print' ;
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
LPAREN : '(' ;
RPAREN : ')' ;

ID : ('_' | LETTER) WORD* ;
INT: '0' | ('+' | '-')? [1-9]NUMBER* ;
FLOAT : INT '.' NUMBER*
      | '.' NUMBER+
      ;                 // 最长优先匹配原则：1.23不会被识别成1和.23

SL_COMMENT : '//' .*? '\n' -> skip ; // 加上?表示非贪婪匹配
SL_COMMENT2 : '//' (~'\n')* '\n' -> skip ; // ~表示非
DOCS_COMMENT : '/**' .*? '*/' -> skip ; // 文档注释的规则必须写在多行注释之前，因为文档注释可以被匹配为多行注释（最前优先匹配原则）
ML_COMMENT : '/*' .*? '*/' -> skip ;

WS : [ \t\r\n]+ -> skip; // skip表示不参与语法分析

fragment LETTER : [a-zA-Z] ;
fragment NUMBER : [0-9] ;
fragment WORD : '_' | LETTER | NUMBER ;