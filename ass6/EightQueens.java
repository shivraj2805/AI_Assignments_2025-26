public class EightQueens {

    static final int N = 8; // 8x8 chessboard

    // Function to print the board
    static void printBoard(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if it’s safe to place a queen at board[row][col]
    static boolean isSafe(int board[][], int row, int col) {
        int i, j;

        // Check this row on the left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        // Check upper diagonal on the left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check lower diagonal on the left side
        for (i = row, j = col; i < N && j >= 0; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Recursive function to solve the 8-Queens problem
    static boolean solveNQUtil(int board[][], int col) {
        // Base case: if all queens are placed
        if (col >= N)
            return true;

        // Try placing queen in each row for this column
        for (int i = 0; i < N; i++) {
            // Check if queen can be placed
            if (isSafe(board, i, col)) {
                board[i][col] = 1; // Place queen

                // Recurse for next column
                if (solveNQUtil(board, col + 1))
                    return true;

                // If placing queen in this row doesn't lead to solution
                board[i][col] = 0; // Backtrack
            }
        }

        // If queen cannot be placed in any row
        return false;
    }

    // Solve and print solution
    static void solveNQ() {
        int board[][] = new int[N][N];

        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
            return;
        }

        printBoard(board);
    }

    // Main function
    public static void main(String[] args) {
        solveNQ();
    }
}
