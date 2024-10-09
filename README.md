# Sol - A language made for my compilers class

This is a fictional language made for the fictional programming language Sol, created my Compilers professor.
The project was a group project and this repo serves as  a "public" release of the compiler, assembler and Virtual machine.

This compiler was made with ANTLR4 as the lexing and parsing engine and the rest was written in Java.

## Rules and features

The language is simple and has a C like syntax, every program must contain
a main function and is composed by function declarations, each function can declare multiple scopes,
and each scope is composed by variable declarations followed by procedures, this is enforced strictly.
```
void main()
begin
    print "Hello world";
end
```

An example of scopes and variable declarations
```
void main()
begin
    int a = 1; int b;
    begin
        int c = 2;
        begin
            int d = 3; int e;
            begin
                int f = 4;
            end
            e = 5;
        end
    end
    b = 6;
    begin
        int g = 7;
    end
end
```
It also supports basic control flow and loops
```
int i;
void main()
begin
    int j = 1;
    for i = 1 to 9 do
        print i + " Hallo :D";

    while j < 10 do
    begin
        print j + " Hallo :D";
        j = j + 1;
    end
end
```
```
void main()
begin
    int n = 10;
    if n % 2 == 0 then
        print "par";
    else
        print "impar";
    print "Fim!";
end
```

## Types and Arrays

The language supports basic primitive types and arrays but no struct or union types.
Basic types are:
    - int
    - real
    - bool
    - string

All of these types support references and dereferences using the "&" operator for reference and "#" operator for dereference

```
void main()
begin

    int x = 1, y = 10;
    int& x_ptr = &x;
    int& y_ptr = &y;

    print &global;

    print x;
    print &x;
    print x_ptr;

    global_double_ptr = &x_ptr;
    print global_double_ptr;
    print #global_double_ptr;
    print ##global_double_ptr;

    print &x == x_ptr;
    print &x != x_ptr;
    print &y == x_ptr;
    print &y != x_ptr;
end

```
Arrays are declared C like 
```
int[10][10] arr;
```
Many more examples are in the inputs directory

## Tools
You can use both the assembler and Compiler and write code in stdin but they do come with flags and arguments you can pass

Compiler: 
```java -jar SolCompiler.jar [-i input_file] [-o output_file] [--asm] [--no-tasm]
```
Assembler: 
```
java -jar TAssembler.jar [-i input_file] [-o output_file] [--asm]
```
Vm: 
```
java -jar TVm.jar [-b binary] [--trace]

```

You can get the tools in the releases tab

