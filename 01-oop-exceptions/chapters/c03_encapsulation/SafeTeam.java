package c03_encapsulation;

import java.util.List;

/** The fix: copy on the way in (defensive copy) and never expose a changeable list. */
public class SafeTeam {

    private static final int MAX_SIZE = 2;

    private final List<String> members;

    public SafeTeam(List<String> members) {
        if (members.size() > MAX_SIZE) {
            throw new IllegalArgumentException("Team is too big");
        }
        this.members = List.copyOf(members); // our own unmodifiable copy
    }

    public List<String> getMembers() {
        return members; // safe: this list cannot be modified
    }
}
