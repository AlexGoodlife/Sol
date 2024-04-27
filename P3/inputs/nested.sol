int i = 1;
int j = 1;

while i < 3 do
begin
    while j < 3 do
    begin
        print "i:  " + i + " j: " + j;
        j = j + 1;
        break;
        break;
    end
    j = 1;
    i = i + 1;
end