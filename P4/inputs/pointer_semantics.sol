real& af;

void int_swap(int& x, int& y)
begin
    int temp = x;
    #x = #y;
    #y = &temp;
end

void main()
begin
    int x = 1, y = 10;
    int_swap(&x, y);
    print &x + &y; //Safely assuming that it works for all operations
    print &x == &y;
    print ##af;
end