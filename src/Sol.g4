grammar Sol;

program:    declaration* functionDeclaration+ EOF
            ;

declaration:    type=(INT_T|DOUBLE_T|STRING_T|BOOLEAN_T) REF* (LSBRACKET INT RSBRACKET)* declarationAssign (',' declarationAssign)* EOL
                ;

declarationAssign:  IDENTIFIER ('=' expr)?
                    ;

functionDeclaration:    type=(INT_T|DOUBLE_T|STRING_T|BOOLEAN_T|VOID) IDENTIFIER LPAREN (argument (',' argument)*)? RPAREN scope
                        ;

argument:   type=(INT_T|DOUBLE_T|STRING_T|BOOLEAN_T) REF* (E_ARRAY)* IDENTIFIER
            ;

scope:  BEGIN declaration* instruction* END
                ;

instruction:    PRINT expr EOL                                          #Print
                | assign EOL                                            #Assignment
                | scope                                                 #Block
                | WHILE expr DO instruction                             #While
                | FOR assign TO expr DO instruction                     #For
                | IF expr THEN instruction (ELSE instruction)?          #If
                | EOL                                                   #Empty
                | BREAK EOL                                             #Break
                | IDENTIFIER LPAREN (expr (',' expr)*)? RPAREN EOL      #VoidFunctionCall
                | RETURN expr? EOL                                      #Return
                ;

assign: DREF* IDENTIFIER (LSBRACKET expr RSBRACKET)* '=' expr
        ;

expr:   LPAREN expr RPAREN                                      #Parentheses
        | op=(MINUS|NOT) expr                                   #Negation
        | expr op=(MULT|DIV|MOD) expr 	                        #MultDivMod
        | expr op=(ADD|MINUS) expr                              #AddSub
        | expr op=(LT|GT|LET|GET) expr                          #Relational
        | expr op=(EQ|NEQ) expr                                 #Equality
        | expr AND expr                                         #And
        | expr OR expr                                          #Or
        | INT        		                                    #Int
        | DOUBLE        		                                #Double
        | STRING        		                                #String
        | BOOLEAN        		                                #Boolean
        | IDENTIFIER                                            #Identifier
        | IDENTIFIER LPAREN (expr (',' expr)*)? RPAREN          #NonVoidFunctionCall
        | REF IDENTIFIER                                        #Reference
        | DREF* IDENTIFIER                                      #Dereference
        | IDENTIFIER (LSBRACKET expr RSBRACKET)+                #ArrayAccess
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
VOID: 'void';
RETURN: 'return';

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
REF: '&';
DREF: '#';

//Arrays
LSBRACKET: '[';
RSBRACKET: ']';
E_ARRAY: LSBRACKET RSBRACKET;

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



