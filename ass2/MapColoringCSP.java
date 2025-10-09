import java.util.*;

public class MapColoringCSP {
    static String[] regions = {"A","B","C","D","E"};
    static String[] colors = {"Red","Green","Blue"};
    static Map<String,List<String>> adj = new HashMap<>();

    static {
        // example adjacency
        adj.put("A", Arrays.asList("B","C"));
        adj.put("B", Arrays.asList("A","C","D"));
        adj.put("C", Arrays.asList("A","B","D","E"));
        adj.put("D", Arrays.asList("B","C","E"));
        adj.put("E", Arrays.asList("C","D"));
    }

    static void backtrack(Map<String,String> assignment, int idx, List<Map<String,String>> solutions) {
        if (idx == regions.length) {
            solutions.add(new HashMap<>(assignment));
            return;
        }
        String var = regions[idx];
        for (String col : colors) {
            if (consistent(var, col, assignment)) {
                assignment.put(var, col);
                backtrack(assignment, idx+1, solutions);
                assignment.remove(var);
            }
        }
    }

    static boolean consistent(String var, String color, Map<String,String> assignment) {
        for (String neighbor : adj.getOrDefault(var, Collections.emptyList())) {
            if (assignment.containsKey(neighbor) && assignment.get(neighbor).equals(color)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Map<String,String>> solutions = new ArrayList<>();
        backtrack(new HashMap<>(), 0, solutions);
        System.out.println("Found " + solutions.size() + " solutions.");
        for (Map<String,String> sol : solutions) {
            System.out.println(sol);
        }
    }
}
