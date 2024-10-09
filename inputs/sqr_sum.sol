int sqr(int x) begin
    return x*x;
end

int sqrsum( int a, int b)
begin
    int s;
    s = sqr(a + b);
    return s;
end

void main()begin
    print sqrsum(3,2);
end