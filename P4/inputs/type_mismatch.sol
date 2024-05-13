/* Type mismatch galore */

void main()
begin
    print not "Good";
    print not 1.0;
    print -false;
    print true % false;
    print true + 1;
    print "Hello" - " World";
    print "10" / "2";
    print true * 1;
    print 1 and 1;
    print 1 or 1;
    print "" < "";
    print 1 == "1";
end