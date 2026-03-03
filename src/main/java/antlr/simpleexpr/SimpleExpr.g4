grammar SimpleExpr; // 语言名与文件名保持一致

prog : stat* EOF ;

stat : expr ';'
     | ID '=' expr ';' // 备选分支
     | 'print' expr ';'
     ;

expr : expr ('*' | '/') expr // 递归
     | expr ('+' | '-') expr
     | '(' expr ')'
     | ID
     ;

ID : ('_' | [a-zA-Z]) ('_' | [a-zA-Z0-9])* ;
WS : [ \t\r\n]+ -> skip; // 表示不参与语法分析