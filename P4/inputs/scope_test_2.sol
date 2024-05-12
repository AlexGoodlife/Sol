int block(int x, int y, int z, int l)
begin
    int j = 2;
    int test = j;
    if(x == 0) then
        return 2;
    return block(x-1,y+1,z,l);
end

void main()
begin
    print block(2,3,4,5);
end