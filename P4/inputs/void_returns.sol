void no_return()
begin
    if true then
        print true;
    else
        return;
end

void return_()
begin
    print "Hello";
    return;
end

void main()
begin
    no_return();
    return_();
    return;
end