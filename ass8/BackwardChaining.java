import java.util.*;

public class BackwardChaining {

    static Map<String, String> rules = new HashMap<>();
    static Set<String> facts = new HashSet<>();

    public static boolean backwardChain(String goal) {
        if (facts.contains(goal))
            return true;

        for (Map.Entry<String, String> rule : rules.entrySet()) {
            if (rule.getValue().equals(goal)) {
                System.out.println("To prove " + goal + ", we need to prove " + rule.getKey());
                if (backwardChain(rule.getKey())) {
                    facts.add(goal);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Knowledge base (rules)
        rules.put("raining", "ground_wet");
        rules.put("ground_wet", "grass_slippery");
        rules.put("grass_slippery", "walk_carefully");

        // Known facts
        facts.add("raining");

        // Goal
        String goal = "walk_carefully";

        if (backwardChain(goal))
            System.out.println("\n✅ Goal '" + goal + "' has been proven true!");
        else
            System.out.println("\n❌ Goal '" + goal + "' cannot be proven.");

        System.out.println("Known facts: " + facts);
    }
}
