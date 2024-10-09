int i = 1;
int j = 2;
int k = 3;

int foo(int i, int j)
begin
    begin
        real x = 2;
        real y = 4;
        begin
            real z = 2;
            real w = 4;
        end
    end
    begin
        int first = 2;
        int second = 4;
    end
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
    return;
end