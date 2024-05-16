int&& global_double_ptr;
int global = 20;

void swap(int& x, int& y)
begin
    int temp = #x;
    #x = #y;
    #y = temp;
end

void double_swap(int&& x, int&& y)
begin
    int temp = ##x;
    ##x = ##y;
    ##y = temp;
end

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

    print "global: " + global + " x: " + x;
    swap(&global, x_ptr);
    print "global: " + global + " x: " + x;

    print "x: " + x + " y: " + y;
    double_swap(global_double_ptr, &y_ptr);
    print "x: " + x + " y: " + y;

    print &x == x_ptr;
    print &x != x_ptr;
    print &y == x_ptr;
    print &y != x_ptr;
end