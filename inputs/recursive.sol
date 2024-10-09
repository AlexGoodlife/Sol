int recursive(int x)begin
    if(x == 0) then return x;
    return recursive(x-1);
end

void main()
begin
    int j = recursive(2);
    print j;
end