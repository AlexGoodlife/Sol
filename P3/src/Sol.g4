grammar Sol;

program:    instruction+ EOF
            ;

instruction:    PRINT expr EOL                                                                          #Print
                | type=(INT_T|DOUBLE_T|STRING_T|BOOLEAN_T) optionalAssign (',' optionalAssign)* EOL     #Declaration
                | assign (',' assign)* EOL                                                              #Assignment
                | BEGIN instruction* END                                                                #Block
                | WHILE expr DO instruction                                                             #While
                | FOR assign TO expr DO instruction                                                     #For
                | IF expr THEN instruction (ELSE instruction)?                                          #If
                | EOL                                                                                   #Empty
                | BREAK EOL                                                                             #Break
                ;

optionalAssign: IDENTEFIER ('=' expr)?
            ;

assign: IDENTEFIER '=' expr
        ;

expr:   LPAREN expr RPAREN                  #Parentheses
        | op=(MINUS|NOT) expr               #Negation
        | expr op=(MULT|DIV|MOD) expr 	    #MultDivMod
        | expr op=(ADD|MINUS) expr          #AddSub
        | expr op=(LT|GT|LET|GET) expr      #Relational
        | expr op=(EQ|NEQ) expr             #Equality
        | expr AND expr                     #And
        | expr OR expr                      #Or
        | INT        		                #Int
        | DOUBLE        		            #Double
        | STRING        		            #String
        | BOOLEAN        		            #Boolean
        | IDENTEFIER                        #Identefier
        ;

//Language types
INT: DIGIT+;
DOUBLE: DIGIT+'.'DIGIT+;
STRING: '"' ('\\"'|.)*? '"';
BOOLEAN: TRUE | FALSE;

//Reserved keywords
PRINT: 'print';
INT_T: 'int';
DOUBLE_T: 'real';
STRING_T: 'string';
BOOLEAN_T: 'bool';
TRUE: 'true';
FALSE: 'false';
BEGIN: 'begin';
END: 'end';
WHILE: 'while';
DO: 'do';
FOR: 'for';
TO: 'to';
IF: 'if';
THEN: 'then';
ELSE: 'else';
BREAK: 'break';

//Operations
MULT: '*';
ADD: '+';
MINUS: '-';
MOD: '%';
DIV: '/';
LPAREN: '(';
RPAREN: ')';
GT: '>';
LT: '<';
GET: '>=';
EQ: '==';
NEQ: '!=';
LET: '<=';
NOT: 'not';
AND: 'and';
OR: 'or';

//Identefier
IDENTEFIER: ('_'|LETTER)('_'|LETTER|DIGIT)*;

//End of line
EOL: ';';

WS: [ \t\r\n]+ -> skip;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip; // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ; // multi-line comment

fragment
DIGIT: [0-9];
LETTER: [A-Za-z];



