/*
* Computes all prime numbers upto n
*/
int i, j, n = 30;
bool isPrime;
string result = "";
for i = 1 to n do
begin
// check if i is prime
isPrime = true;
for j = 2 to i/2 do
if i % j == 0 then
begin
isPrime = false;
break;
end
if isPrime
then result = result + " " + i;
end
print "Prime numbers upto " + n + ":" + result;