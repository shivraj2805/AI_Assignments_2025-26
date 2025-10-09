import java.util.*;

public class MapColoringCSP {

    // Variables (states to color)
    static final List<String> VARIABLES = Arrays.asList("WA","NT","SA","Q","NSW","V","T");

    // Domains (available colors for each state)
    static Map<String, List<String>> domains = new HashMap<>();

    // Constraints (adjacency list: neighbors must have different colors)
    static Map<String, List<String>> neighbors = new HashMap<>();

    public static void main(String[] args) {
        initDomains(Arrays.asList("Red", "Green", "Blue"));
        initNeighbors();

        Map<String, String> assignment = new HashMap<>();
        boolean solved = backtrack(assignment);

        if (solved) {
            System.out.println("Solution:");
            for (String var : VARIABLES) {
                System.out.println(var + " -> " + assignment.get(var));
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    // Initialize domains: each variable gets the same list of colors
    static void initDomains(List<String> colors) {
        for (String v : VARIABLES) {
            domains.put(v, new ArrayList<>(colors));
        }
    }

    // Australia map adjacency
    static void initNeighbors() {
        addEdges("WA", "NT", "SA");
        addEdges("NT", "WA", "SA", "Q");
        addEdges("SA", "WA", "NT", "Q", "NSW", "V");
        addEdges("Q", "NT", "SA", "NSW");
        addEdges("NSW", "Q", "SA", "V");
        addEdges("V", "SA", "NSW");
        addEdges("T"); // Tasmania has no mainland neighbors
    }

    static void addEdges(String var, String... neighs) {
        neighbors.putIfAbsent(var, new ArrayList<>());
        for (String n : neighs) {
            neighbors.get(var).add(n);
            neighbors.putIfAbsent(n, new ArrayList<>());
            if (!neighbors.get(n).contains(var)) {
                neighbors.get(n).add(var); // ensure undirected
            }
        }
    }

    // Backtracking search
    static boolean backtrack(Map<String, String> assignment) {
        if (assignment.size() == VARIABLES.size()) return true;

        String var = selectUnassignedVariable(assignment);

        for (String value : domains.get(var)) {
            if (isConsistent(var, value, assignment)) {
                assignment.put(var, value);
                if (backtrack(assignment)) return true;
                assignment.remove(var);
            }
        }
        return false;
    }

    // Simple variable selection: first unassigned (keep it simple)
    static String selectUnassignedVariable(Map<String, String> assignment) {
        for (String v : VARIABLES) {
            if (!assignment.containsKey(v)) return v;
        }
        return null; // should never happen
    }

    // Check all constraints with already-assigned neighbors
    static boolean isConsistent(String var, String value, Map<String, String> assignment) {
        for (String n : neighbors.getOrDefault(var, Collections.emptyList())) {
            String assigned = assignment.get(n);
            if (assigned != null && assigned.equals(value)) {
                return false; // neighbor has same color
            }
        }
        return true;
    }
}
