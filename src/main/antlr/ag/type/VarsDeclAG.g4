grammar VarsDeclAG;

@header {
package ag.type;
}

//decl : type vars[$type.text] ;
type : 'int'
     | 'float'
     ;
// 此处antlr4有bug，无法处理左递归嵌入
//vars[String typeStr]
//     : vars[$typeStr] ',' ID
//            { System.out.println($ID.text + ":" + $typeStr); }
//     | ID   { System.out.println($ID.text + ":" + $typeStr); }
//     ;

ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;