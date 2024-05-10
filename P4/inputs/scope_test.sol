int i = 1;
int j = 2;
real k = 3;

int foo(int i, int j)
begin
    return i + j + k;
end
void main()
begin
    int i = 1;
    int scoped = 2;
    j = i;
    begin
        int i = 3;
        k = k + scoped;
        k = k + i;
    end
    return i;
end