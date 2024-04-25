/* Declaration errors */

int i, j = 0;
real pi = 3.14;
string hello = "Hello", white_space = " ", world = "World";
bool b = true;

real pi = 6.28; //Re-declaration
real e = 3; //Type mismatch

pi = 6.28;
e = 2.71;

tau = 2 * pi; //Undeclared tau variable
pi = "3.14"; //Type mismatch


/* Expressions using variables */

print tau == pi * 2; //Undeclared tau variable
print hello + white_space + world;

//Type errors in expressions should still work with variables
/* Type mismatch galore v2*/

print not hello;
print not pi;
print -b;
print b % b;
print b + j;
print hello - world;
print hello / world;
print b * pi;
print j and pi;
print pi or j;
print world < hello;
print pi == white_space;


/* While loop */

while pi == 3.14 do
	;

while pi do //Type mismatch
	;


/* For loop */

for i = 0 to 100 * j do
	;

for i = 1 to 100.0 do //Type mismatch
	;

for pi = 3.14 to 1000 do //Type mismatch
	;

for pi = 3.14 to 1000.0 do //Type mismatch
	;


/* If statements */

if b then
	;
else
	;

if pi then //Type mismatch
	;
else if tau then //Undeclared tau variable
	;
else
	;


/* Break instruction */

break; //break out of loop

while true do
	break;

string hello_world = hello + white_space + world;


/* Assure that syntax errors don't cause Java internal errors in here Ig */
