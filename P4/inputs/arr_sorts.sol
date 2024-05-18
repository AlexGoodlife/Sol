void gen_collatz_arr(int[] arr, int size, int first_val)
begin
    int i;
    int curr_val = first_val;

    for i = 0 to size - 1 do
    begin
        arr[i] = curr_val;
        if curr_val % 2 == 0 then
            curr_val = curr_val / 2;
        else
            curr_val = 3 * curr_val + 1;
    end
end

void print_arr(int[] arr, int size)
begin
    string arr_s = "[" + arr[0];
    int i = 0;
    for i = 1 to size - 1 do
        arr_s = arr_s + ", " + arr[i];
    arr_s = arr_s + "]";
    print arr_s;
end

void isort(int[] arr, int size)
begin
    int i, j;
    for i = 1 to size - 1 do
    begin
        j = i;
        while j > 0 do
        begin
            if arr[j - 1] <= arr[j] then
                break;
            arr_swap(arr, j, j - 1);
            j = j - 1;
        end
    end
end

void arr_swap(int[] arr, int i, int j)
begin
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
end

void main()
begin
    int[10] arr;
    gen_collatz_arr(arr, 10, 10);

    print "Before: ";
    print_arr(arr, 10);

    isort(arr, 10);

    print "\nAfter: ";
    print_arr(arr, 10);
end