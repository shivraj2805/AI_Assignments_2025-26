import java.util.*;

public class ForwardChaining {

    public static void main(String[] args) {
        // Knowledge Base (Rules)
        Map<String, String> rules = new HashMap<>();
        rules.put("raining", "ground_wet");
        rules.put("ground_wet", "grass_slippery");
        rules.put("grass_slippery", "walk_carefully");

        // Initial facts
        Set<String> facts = new HashSet<>();
        facts.add("raining");

        // Goal
        String goal = "walk_carefully";

        boolean found = false;

        while (true) {
            boolean newFactAdded = false;
            for (Map.Entry<String, String> rule : rules.entrySet()) {
                if (facts.contains(rule.getKey()) && !facts.contains(rule.getValue())) {
                    System.out.println("Applying rule: IF " + rule.getKey() + " THEN " + rule.getValue());
                    facts.add(rule.getValue());
                    newFactAdded = true;

                    if (rule.getValue().equals(goal)) {
                        found = true;
                        break;
                    }
                }
            }
            if (found || !newFactAdded) break;
        }

        if (found)
            System.out.println("\n✅ Goal '" + goal + "' has been reached!");
        else
            System.out.println("\n❌ Goal could not be reached.");

        System.out.println("Known facts: " + facts);
    }
}
