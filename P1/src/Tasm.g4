grammar Tasm;

program:    (statement)+
            ;

statement:  (instruction EOL)+ HALT EOL
            ;

instruction:    LOAD_INT INT                                                #LoadInt
                | LOAD_DOUBLE DOUBLE                                        #LoadDouble
                | LOAD_STRING STRING                                        #LoadString
                | LOAD_BOOL BOOL                                            #LoadBool
                | global=(GLOBAL_ALLOC | GLOBAL_LOAD | GLOBAL_STORE) INT    #Global
                | SIMPLE_INSTRUCTION                                        #SimpleInstruction //Will need hash map or a giant switch case statement
                ;

HALT: 'halt';
EOL: '\n';

//Language types
INT: DIGIT+;
DOUBLE: DIGIT+'.'DIGIT+;
STRING: '"'CHARACTER*'"';
BOOL: 'true' | 'false';
NULL: 'NIL';

//One argument instructions
LOAD_INT: 'iconst';
LOAD_DOUBLE: 'dconst';
LOAD_STRING: 'sconst';
LOAD_BOOL: 'bconst';
UJUMP: 'jump';
CJUMP_TRUE: 'jumpt';
CJUMP_FALSE: 'jumpf';
GLOBAL_ALLOC: 'galloc';
GLOBAL_LOAD: 'gload';
GLOBAL_STORE: 'gstore';

//Simple instructions
SIMPLE_INSTRUCTION: 'iprint' | 'iuminus' | 'iadd' | 'isub' | 'imult' | 'idiv' | 'imod' | 'ieq' | 'ineq' | 'ilt'
                    | 'ileq' | 'itod' | 'itos'
                    | 'dprint' | 'duminus' | 'dadd' | 'dsub' | 'dmult' | 'ddiv' | 'deq' | 'dneq' | 'dlt' | 'dleq'
                    | 'dtos'
                    ;

WS: [ \r\t]+ -> skip;

fragment
DIGIT: [0-9];
CHARACTER: [A-Za-z]; //TODO: Better implement CHARACTER



