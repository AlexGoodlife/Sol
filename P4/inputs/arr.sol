//int[10][10] arr;

//int[10] a, b;
int[2] a;
int[2][2] b;
void foo(int[] arr)
begin
    int[2] x;
    x[0] = 4;
    x[1] = 5;
    print x[0];
    print x[1];
    print arr[0];
    print arr[1];
    return;
end

void main()
begin
    a[0] = 1;
    a[1] = 2;
    b[0][0] = 1;
    print b[0][0];
    print a[0];
    print a[1];
    foo(a);
    //int[727][2] a;
end