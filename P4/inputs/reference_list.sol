int x;

void main()
begin
    int& x_ptr = &x;
    int y = 2;
    int& y_ptr = &y;
    int&& x_ptr_2 = &x_ptr;
    int wtv = ##x_ptr_2;
end