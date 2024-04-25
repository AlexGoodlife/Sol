grammar Sol;

program:    declaration* instruction+ EOF
            ;

declaration:    type=(INT_T|DOUBLE_T|STRING_T|BOOLEAN_T) declarationAssign (',' declarationAssign)* EOL
                ;

declarationAssign:  IDENTIFIER ('=' expr)?
                    ;

instruction:    PRINT expr EOL                                      #Print
                | assign EOL                                        #Assignment
                | BEGIN instruction* END                            #Block
                | WHILE expr DO instruction                         #While
                | FOR assign TO expr DO instruction                 #For
                | IF expr THEN instruction (ELSE instruction)?      #If
                | EOL                                               #Empty
                | BREAK EOL                                         #Break
                ;

assign: IDENTIFIER '=' expr
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
        | IDENTIFIER                        #Identifier
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

//Identifier
IDENTIFIER: ('_'|LETTER)('_'|LETTER|DIGIT)*;

//End of line
EOL: ';';

WS: [ \t\r\n]+ -> skip;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip; // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ; // multi-line comment

fragment
DIGIT: [0-9];
LETTER: [A-Za-z];



