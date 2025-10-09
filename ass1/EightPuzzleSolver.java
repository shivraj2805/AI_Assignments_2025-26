// EightPuzzleSolver.java
import java.util.*;

public class EightPuzzleSolver {
    static final String GOAL = "123456780"; // 0 is blank

    // Moves: up, down, left, right by swapping '0' with neighbor
    static List<String> neighbors(String state) {
        int pos = state.indexOf('0');
        int r = pos / 3, c = pos % 3;
        List<String> res = new ArrayList<>();
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] d : dirs) {
            int nr = r + d[0], nc = c + d[1];
            if (nr>=0 && nr<3 && nc>=0 && nc<3) {
                int npos = nr*3 + nc;
                char[] arr = state.toCharArray();
                char tmp = arr[npos]; arr[npos] = arr[pos]; arr[pos] = tmp;
                res.add(new String(arr));
            }
        }
        return res;
    }

    // BFS: returns path from start to goal or null
    static List<String> bfs(String start) {
        if (start.equals(GOAL)) return Arrays.asList(start);
        Queue<String> q = new LinkedList<>();
        Map<String,String> parent = new HashMap<>();
        q.add(start); parent.put(start, null);
        while (!q.isEmpty()) {
            String cur = q.poll();
            for (String nb : neighbors(cur)) {
                if (!parent.containsKey(nb)) {
                    parent.put(nb, cur);
                    if (nb.equals(GOAL)) {
                        List<String> path = new ArrayList<>();
                        String p = nb;
                        while (p!=null) { path.add(p); p = parent.get(p); }
                        Collections.reverse(path);
                        return path;
                    }
                    q.add(nb);
                }
            }
        }
        return null;
    }

    // DFS with depth limit: returns path or null
    static List<String> dfs(String start, int limit) {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        boolean found = dfsHelper(start, limit, visited, path);
        return found ? path : null;
    }

    static boolean dfsHelper(String cur, int limit, Set<String> visited, List<String> path) {
        visited.add(cur); path.add(cur);
        if (cur.equals(GOAL)) return true;
        if (limit == 0) { path.remove(path.size()-1); visited.remove(cur); return false; }
        for (String nb : neighbors(cur)) {
            if (!visited.contains(nb)) {
                if (dfsHelper(nb, limit-1, visited, path)) return true;
            }
        }
        path.remove(path.size()-1); visited.remove(cur);
        return false;
    }

    static void printPath(List<String> path) {
        if (path==null) { System.out.println("No solution found."); return; }
        for (String s : path) {
            for (int i=0;i<9;i++) {
                System.out.print(s.charAt(i)=='0' ? " " : s.charAt(i));
                if (i%3==2) System.out.println();
                else System.out.print(" ");
            }
            System.out.println("----");
        }
        System.out.println("Steps: " + (path.size()-1));
    }

    public static void main(String[] args) {
        // A solvable start (moderate)
        String start = "724506831"; // example
        System.out.println("Start:");
        printPath(Arrays.asList(start));
        System.out.println("BFS solving...");
        List<String> bfsPath = bfs(start);
        printPath(bfsPath);

        System.out.println("DFS solving with depth limit 20...");
        List<String> dfsPath = dfs(start, 20);
        printPath(dfsPath);
    }
}
