grammar Tasm;

program:    (line)+
            ;

line:   ((LABEL':')?instruction)? EOL
        ;

instruction:    instructionWithArguments
                | simpleInstruction    //Will need hash map or a giant switch case statement later
                ;

instructionWithArguments:   LOAD_INT INT                                                    #LoadInt
                            | LOAD_DOUBLE value = (DOUBLE | INT)                            #LoadDouble
                            | LOAD_STRING STRING                                            #LoadString
                            | LOAD_BOOL BOOL                                                #LoadBool
                            | jump = (UJUMP | CJUMP_TRUE | CJUMP_FALSE) LABEL               #Jump
                            | operation = (GLOBAL_ALLOC | GLOBAL_LOAD | GLOBAL_STORE) INT   #Global
                            ;

simpleInstruction:  SIMPLE_INSTRUCTION
                    ;

//Language types
INT: DIGIT+;
DOUBLE: DIGIT+'.'DIGIT+;
STRING: '"'.*?'"';
BOOL: 'true' | 'false';
NULL: 'NIL';

//One argument instructions
//Load instructions
LOAD_INT: 'iconst';
LOAD_DOUBLE: 'dconst';
LOAD_STRING: 'sconst';
LOAD_BOOL: 'bconst';
//Jump instructions
UJUMP: 'jump';
CJUMP_TRUE: 'jumpt';
CJUMP_FALSE: 'jumpf';
//Global memory instructions
GLOBAL_ALLOC: 'galloc';
GLOBAL_LOAD: 'gload';
GLOBAL_STORE: 'gstore';

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
                    | 'bprint' | 'beq' | 'bneq' | 'and' | 'or' | 'not' | 'btos'
                    //Halt instruction
                    | 'halt'
                    ;

LABEL: ('_'|LETTER)('_'|LETTER|DIGIT)+;
EOL: '\n';

WS: [ \r\t]+ -> skip;

fragment
DIGIT: [0-9];
LETTER: [A-Za-z];



