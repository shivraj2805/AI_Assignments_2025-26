import java.util.*;

public class TicTacToeMinimax {
    static final char EMPTY='.';
    static final char X='X', O='O';

    static void printBoard(char[][] b) {
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++) System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }

    static boolean isMovesLeft(char[][] b) {
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) if (b[i][j]==EMPTY) return true;
        return false;
    }

    static int evaluate(char[][] b) {
        // rows, cols, diagonals
        for (int i=0;i<3;i++) {
            if (b[i][0]==b[i][1] && b[i][1]==b[i][2]) {
                if (b[i][0]==X) return +10;
                if (b[i][0]==O) return -10;
            }
        }
        for (int j=0;j<3;j++) {
            if (b[0][j]==b[1][j] && b[1][j]==b[2][j]) {
                if (b[0][j]==X) return +10;
                if (b[0][j]==O) return -10;
            }
        }
        if (b[0][0]==b[1][1] && b[1][1]==b[2][2]) {
            if (b[0][0]==X) return +10;
            if (b[0][0]==O) return -10;
        }
        if (b[0][2]==b[1][1] && b[1][1]==b[2][0]) {
            if (b[0][2]==X) return +10;
            if (b[0][2]==O) return -10;
        }
        return 0;
    }

    static int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board);
        if (score==10) return score - depth;  // prefer faster wins
        if (score==-10) return score + depth; // prefer slower losses
        if (!isMovesLeft(board)) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i=0;i<3;i++) for (int j=0;j<3;j++) {
                if (board[i][j]==EMPTY) {
                    board[i][j]=X;
                    best = Math.max(best, minimax(board, depth+1, false));
                    board[i][j]=EMPTY;
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i=0;i<3;i++) for (int j=0;j<3;j++) {
                if (board[i][j]==EMPTY) {
                    board[i][j]=O;
                    best = Math.min(best, minimax(board, depth+1, true));
                    board[i][j]=EMPTY;
                }
            }
            return best;
        }
    }

    static int[] findBestMove(char[][] board) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1,-1};
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) {
            if (board[i][j]==EMPTY) {
                board[i][j]=X;
                int moveVal = minimax(board, 0, false);
                board[i][j]=EMPTY;
                if (moveVal > bestVal) {
                    bestVal = moveVal; bestMove[0]=i; bestMove[1]=j;
                }
            }
        }
        return bestMove;
    }

    public static void main(String[] args) {
        char[][] board = {
            {X, O, X},
            {O, X, EMPTY},
            {EMPTY, O, EMPTY}
        };
        System.out.println("Current board:");
        printBoard(board);
        int[] mv = findBestMove(board);
        System.out.println("Best move for X: " + mv[0] + ", " + mv[1]);
        if (mv[0]!=-1) {
            board[mv[0]][mv[1]] = X;
            System.out.println("Board after move:");
            printBoard(board);
        } else {
            System.out.println("No moves left / game over.");
        }
    }
}
