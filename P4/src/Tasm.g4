grammar Tasm;

program:    line (EOL+ line)* EOL* EOF
            ;

line:   (LABEL (',' LABEL)* ':')? instruction
        ;

instruction:    LOAD_INT INT                                                    #LoadInt
                | LOAD_DOUBLE value = (DOUBLE | INT)                            #LoadDouble
                | LOAD_STRING STRING                                            #LoadString
                | jump = (UJUMP | CJUMP_TRUE | CJUMP_FALSE | CALL) LABEL        #Jump
                | global = (GLOBAL_ALLOC | GLOBAL_LOAD | GLOBAL_STORE) INT      #Global
                | local = (LOCAL_ALLOC | LOCAL_LOAD | LOCAL_STORE | POP) INT    #Local
                | return = (RETURN_VALUE | RETURN) INT                          #Return
                | SIMPLE_INSTRUCTION                                            #Simple
                ;

//Language types
INT: '-'?DIGIT+;
DOUBLE: '-'?DIGIT+'.'DIGIT+;
STRING: '"' ('\\"'|.)*? '"';
//One argument instructions
//Load instructions
LOAD_INT: 'iconst';
LOAD_DOUBLE: 'dconst';
LOAD_STRING: 'sconst';
//Jump instructions
UJUMP: 'jump';
CJUMP_TRUE: 'jumpt';
CJUMP_FALSE: 'jumpf';
//Global memory instructions
GLOBAL_ALLOC: 'galloc';
GLOBAL_LOAD: 'gload';
GLOBAL_STORE: 'gstore';
//Local memory instructions
LOCAL_ALLOC: 'lalloc';
LOCAL_LOAD: 'lload';
LOCAL_STORE: 'lstore';
POP: 'pop';
//Function instructions
CALL: 'call';
RETURN_VALUE: 'retval';
RETURN: 'ret';

//Simple instructions
SIMPLE_INSTRUCTION: //Int instructions
                    'iprint' | 'iuminus' | 'iadd' | 'isub' | 'imult' | 'idiv' | 'imod' | 'ieq' | 'ineq' | 'ilt'
                    | 'ileq' | 'itod' | 'itos'
                    //double instructions
                    | 'dprint' | 'duminus' | 'dadd' | 'dsub' | 'dmult' | 'ddiv' | 'deq' | 'dneq' | 'dlt' | 'dleq'
                    | 'dtos'
                    //String instructions
                    | 'sprint' | 'sadd' | 'seq' | 'sneq'
                    //Bool instructions
                    | 'tconst' | 'fconst' | 'bprint' | 'beq' | 'bneq' | 'and' | 'or' | 'not' | 'btos'
                    //Reference instructions
                    | 'rprint' | 'ldref' | 'lref'
                    //Halt instruction
                    | 'halt'
                    ;

LABEL: ('_'|LETTER)('_'|LETTER|DIGIT)*;
EOL: '\r'?'\n';

WS: [ \r\t]+ -> skip;

fragment
DIGIT: [0-9];
LETTER: [A-Za-z];



