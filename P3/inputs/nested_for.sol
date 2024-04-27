int i = 1;
int j = 1;
int k = 1;
int limit = 3;

for i = 1 to limit do
begin
    for j = 1 to limit do
    begin
        print "i:  " + i + " j: " + j;
        for k = 1 to limit do
        begin
            print "We love K";
            break;
        end
        break;
    end
    break;
    j = 1;
end