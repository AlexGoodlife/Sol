grammar Sol;

program:    line+ EOF
            ;

line:   PRINT expr EOL
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
        ;

//Language types
INT: DIGIT+;
DOUBLE: DIGIT+'.'DIGIT+;
STRING: '"' ('\\"'|.)*? '"';
BOOLEAN: 'true' | 'false';

//Operations
PRINT: 'print';
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

EOL: ';';

WS: [ \t\r\n]+ -> skip;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip; // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ; // multi-line comment

fragment
DIGIT: [0-9];
LETTER: [A-Za-z];



