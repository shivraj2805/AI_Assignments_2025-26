import java.util.*;

public class FamilyTreeKB {
    Map<String, Set<String>> children = new HashMap<>();

    void addParentChild(String parent, String child) {
        children.computeIfAbsent(parent, k -> new HashSet<>()).add(child);
        children.computeIfAbsent(child, k -> new HashSet<>());
    }

    boolean isParent(String p, String c) {
        return children.getOrDefault(p, Collections.emptySet()).contains(c);
    }

    Set<String> getChildren(String p) {
        return children.getOrDefault(p, Collections.emptySet());
    }

    Set<String> getParents(String person) {
        Set<String> res = new HashSet<>();
        for (Map.Entry<String, Set<String>> e : children.entrySet()) {
            if (e.getValue().contains(person)) res.add(e.getKey());
        }
        return res;
    }

    boolean isSibling(String a, String b) {
        if (a.equals(b)) return false;
        Set<String> pa = getParents(a), pb = getParents(b);
        for (String p : pa) if (pb.contains(p)) return true;
        return false;
    }

    boolean isAncestor(String anc, String desc) {
        Set<String> visited = new HashSet<>();
        return isAncestorDfs(anc, desc, visited);
    }

    private boolean isAncestorDfs(String cur, String target, Set<String> visited) {
        if (visited.contains(cur)) return false;
        visited.add(cur);
        for (String ch : getChildren(cur)) {
            if (ch.equals(target)) return true;
            if (isAncestorDfs(ch, target, visited)) return true;
        }
        return false;
    }

    Set<String> getAncestors(String person) {
        Set<String> res = new HashSet<>();
        for (String p : children.keySet()) {
            if (isAncestor(p, person)) res.add(p);
        }
        return res;
    }

    Set<String> getDescendants(String person) {
        Set<String> res = new HashSet<>();
        Deque<String> dq = new ArrayDeque<>();
        dq.addAll(getChildren(person));
        while (!dq.isEmpty()) {
            String cur = dq.poll();
            if (!res.contains(cur)) {
                res.add(cur);
                dq.addAll(getChildren(cur));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FamilyTreeKB kb = new FamilyTreeKB();
        // Facts
        kb.addParentChild("John", "Alice");
        kb.addParentChild("Mary", "Alice");
        kb.addParentChild("John", "Bob");
        kb.addParentChild("Bob", "Cathy");
        kb.addParentChild("Alice", "Derek");

        System.out.println("Is John parent of Alice? " + kb.isParent("John","Alice"));
        System.out.println("Is Alice sibling of Bob? " + kb.isSibling("Alice","Bob"));
        System.out.println("Is John ancestor of Derek? " + kb.isAncestor("John","Derek"));
        System.out.println("Ancestors of Derek: " + kb.getAncestors("Derek"));
        System.out.println("Descendants of John: " + kb.getDescendants("John"));
    }
}
