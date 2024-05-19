
void merge_arrays(int[] a, int low, int mid, int high, int[] c)
begin
   int left = low;
   int right = mid + 1;
   int result = 0;
   int i;
   while(left <= mid and right <= high) do
   begin
        if a[left] <= a[right] then
        begin
            c[result] = a[left];
            result = result + 1;
            left = left + 1;
        end
        else
        begin
            c[result] = a[right];
            result = result + 1;
            right = right + 1;
        end
   end

   while left <= mid do
   begin
        c[result] = a[left];
        result = result + 1;
        left = left + 1;
   end
   while right <= high do
   begin
        c[result] = a[right];
        result = result + 1;
        right = right + 1;
   end
   for i = low to high do
   begin
        a[i] = c[i-low];
   end
end

void merge_sort(int[] a, int[] temp, int low, int high)
begin
    int mid;
    if low >= high then return;
    mid = (low + high)/2;
    merge_sort(a,temp,low,mid);
    merge_sort(a,temp,mid + 1,high);
    merge_arrays(a, low, mid, high, temp);
end

void main()
begin
    int [50] a;
    int [100] tmp;
    int i;
    int n = 50; // Will always be the array size
    int curr_val = 229; // generate random numbers
    for i = 0 to n-1 do
    begin
        a[i] = curr_val;
        if curr_val % 2 == 0 then
            curr_val = curr_val / 2;
        else
            curr_val = 3 * curr_val + 1;
   end
   merge_sort(a,tmp,0,n-1);
    for i = 0 to n-1 do
    begin
        print a[i];
    end
end