grammar Sol;

program:    line+ EOF
            ;

line:   'print' expr ';'
        ;

expr:   op=(MINUS|NOT) expr    #Negation
       | expr op=(MULT|DIV|MOD) expr 	# MultDiv
       | expr op=(ADD|MINUS) expr     # AddSub
       | expr op=(LT|GT|LET|GET) expr     # AddSub
       | expr op=(EQ|NEQ) expr     # AddSub
       | expr AND expr     # AddSub
       | expr OR expr     # AddSub
       | LPAREN expr RPAREN      #Parentheses
       | INT        		# Int
       | DOUBLE        		# Double
       | BOOLEAN        		# Bool
       | STRING        		# String
       ;



//Language types
INT: '-'?DIGIT+;
DOUBLE: '-'?DIGIT+'.'DIGIT+;
STRING: '"' ('\\"'|.)*? '"';
MULT: '*' ;
ADD : '+' ;
MINUS : '-' ;
MOD : '%' ;
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
BOOLEAN: 'true' | 'false';
OR: 'or';

WS: [ \t\r\n]+ -> skip;
SL_COMMENT : '//' .*? (EOF|'\n') -> skip; // single-line comment
ML_COMMENT : '/*' .*? '*/' -> skip ; // multi-line comment
fragment
DIGIT: [0-9];
LETTER: [A-Za-z];



