import java.util.*;

public class AStarGrid {
    static class Node {
        int r,c; int g,h;
        Node parent;
        Node(int r,int c){this.r=r;this.c=c;}
        int f(){ return g+h; }
        public String toString(){return "(" + r + "," + c + ")";}
    }

    static List<Node> getNeighbors(Node node, int[][] grid) {
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        List<Node> out = new ArrayList<>();
        for (int[] d : dirs) {
            int nr=node.r+d[0], nc=node.c+d[1];
            if (nr>=0 && nr<grid.length && nc>=0 && nc<grid[0].length && grid[nr][nc]==0) {
                out.add(new Node(nr,nc));
            }
        }
        return out;
    }

    static int manhattan(int r1,int c1,int r2,int c2){ return Math.abs(r1-r2)+Math.abs(c1-c2); }

    static List<Node> reconstruct(Node target) {
        List<Node> path = new ArrayList<>();
        Node cur = target;
        while (cur!=null) { path.add(cur); cur = cur.parent; }
        Collections.reverse(path);
        return path;
    }

    static List<Node> aStar(int[][] grid, int sr, int sc, int tr, int tc) {
        int rows = grid.length, cols = grid[0].length;
        Node start = new Node(sr,sc);
        Node target = null;
        Comparator<Node> comp = Comparator.comparingInt(Node::f).thenComparingInt(n->n.h);
        PriorityQueue<Node> open = new PriorityQueue<>(comp);
        Map<String, Node> all = new HashMap<>();
        String key = sr + "," + sc;
        start.g = 0; start.h = manhattan(sr,sc,tr,tc);
        open.add(start); all.put(key,start);

        Set<String> closed = new HashSet<>();
        while (!open.isEmpty()) {
            Node cur = open.poll();
            String curKey = cur.r + "," + cur.c;
            if (cur.r==tr && cur.c==tc) { target = cur; break; }
            closed.add(curKey);
            for (Node nb : getNeighbors(cur, grid)) {
                String nbKey = nb.r + "," + nb.c;
                if (closed.contains(nbKey)) continue;
                int tentativeG = cur.g + 1;
                Node existing = all.get(nbKey);
                if (existing == null || tentativeG < existing.g) {
                    Node toUse = (existing==null) ? nb : existing;
                    toUse.g = tentativeG;
                    toUse.h = manhattan(nb.r,nb.c,tr,tc);
                    toUse.parent = cur;
                    if (existing==null) {
                        all.put(nbKey, toUse);
                        open.add(toUse);
                    } else {
                        // reinsert to update priority
                        open.remove(existing);
                        open.add(existing);
                    }
                }
            }
        }
        if (target==null) return null;
        return reconstruct(target);
    }

    public static void main(String[] args) {
        // 0 = free, 1 = obstacle
        int[][] grid = {
            {0,0,0,0,0},
            {0,1,1,1,0},
            {0,0,0,1,0},
            {0,1,0,0,0},
            {0,0,0,1,0}
        };
        int sr=0, sc=0, tr=4, tc=4;
        System.out.println("A* from " + sr+"," + sc + " to " + tr + "," + tc);
        List<Node> path = aStar(grid, sr, sc, tr, tc);
        if (path==null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Path:");
            for (Node n : path) System.out.print(n + " ");
            System.out.println("\nSteps: " + (path.size()-1));
        }
    }
}
