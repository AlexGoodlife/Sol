void gen_collatz_s_matrix(int[][] matrix, int size, int first_val)
begin
    int i, j;
    int curr_val = first_val;

    for i = 0 to size - 1 do
        for j = 0 to size - 1 do
        begin
            matrix[i][j] = curr_val;
            if curr_val % 2 == 0 then
                curr_val = curr_val / 2;
            else
                curr_val = 3 * curr_val + 1;
        end
end

void print_s_matrix(int[][] matrix, int size)
begin
    int i, j;
    for i = 0 to size - 1 do
    begin
        string line_s = "[" + matrix[i][0];
        for j = 1 to size - 1 do
            line_s = line_s + ", " + matrix[i][j];
        line_s = line_s + "]";
        print line_s;
    end
end

void s_matrix_mult(int[][] matrix_1, int[][] matrix_2, int size, int[][] result)
begin
    int i, j, k;
    for i = 0 to size - 1 do
        for j = 0 to size - 1 do
        begin
            result[i][j] = 0;
            for k = 0 to size - 1 do
                result[i][j] = result[i][j] + matrix_1[i][k] * matrix_2[k][j];
        end
end

void main()
begin
    int[4][4] matrix_1;
    int[4][4] matrix_2;
    int[4][4] result;

    gen_collatz_s_matrix(matrix_1, 4, 10);
    print_s_matrix(matrix_1, 4);

    print "\n*\n";

    gen_collatz_s_matrix(matrix_2, 4, 5);
    print_s_matrix(matrix_2, 4);

    print "\n=\n";

    s_matrix_mult(matrix_1, matrix_2, 4, result);
    print_s_matrix(result, 4);
end