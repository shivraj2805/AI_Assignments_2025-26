import java.util.*;

public class EightPuzzle {

    // Goal state
    static final String GOAL = "123456780";

    // Moves: up, down, left, right
    static final int[][] MOVES = {
        {-1, 0}, // up
        {1, 0},  // down
        {0, -1}, // left
        {0, 1}   // right
    };

    public static void main(String[] args) {
        String start = "125340678"; // example start state

        System.out.println("BFS Solution:");
        bfs(start);

        System.out.println("\nDFS Solution:");
        dfs(start);
    }

    // ---------------- BFS ----------------
    static void bfs(String start) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String state = queue.poll();

            if (state.equals(GOAL)) {
                printPath(parent, state);
                return;
            }

            for (String next : getNeighbors(state)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, state);
                    queue.add(next);
                }
            }
        }
        System.out.println("No solution found using BFS.");
    }

    // ---------------- DFS ----------------
    static void dfs(String start) {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        stack.push(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            String state = stack.pop();

            if (state.equals(GOAL)) {
                printPath(parent, state);
                return;
            }

            for (String next : getNeighbors(state)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parent.put(next, state);
                    stack.push(next);
                }
            }
        }
        System.out.println("No solution found using DFS.");
    }

    // Generate possible moves
    static List<String> getNeighbors(String state) {
        List<String> neighbors = new ArrayList<>();
        int zeroIndex = state.indexOf('0');
        int row = zeroIndex / 3;
        int col = zeroIndex % 3;

        for (int[] move : MOVES) {
            int newRow = row + move[0];
            int newCol = col + move[1];

            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int newIndex = newRow * 3 + newCol;
                char[] newState = state.toCharArray();
                // swap blank with target
                char temp = newState[zeroIndex];
                newState[zeroIndex] = newState[newIndex];
                newState[newIndex] = temp;
                neighbors.add(new String(newState));
            }
        }
        return neighbors;
    }

    // Print path from start to goal
    static void printPath(Map<String, String> parent, String state) {
        List<String> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = parent.get(state);
        }
        Collections.reverse(path);

        for (String s : path) {
            printState(s);
        }
        System.out.println("Steps: " + (path.size() - 1));
    }

    // Display puzzle in 3x3 format
    static void printState(String state) {
        for (int i = 0; i < 9; i++) {
            System.out.print(state.charAt(i) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();
    }
}
