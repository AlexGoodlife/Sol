int previous, result, i, n, temp;

previous = 0;
result = 1;
n = 46;

if n == 0 then
    result = previous;
else
    for i = 1 to n - 1 do
    begin
        temp = result;
        result = previous + result;
        previous = temp;
    end

print result;