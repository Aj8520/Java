package c03_encapsulation;

import java.util.List;

/**
 * Looks encapsulated (private final field, no setter) but is not.
 * The real list leaks in through the constructor and out through the getter.
 */
public class LeakyTeam {

    private static final int MAX_SIZE = 2;

    private final List<String> members;

    public LeakyTeam(List<String> members) {
        if (members.size() > MAX_SIZE) {
            throw new IllegalArgumentException("Team is too big");
        }
        this.members = members; // keeps the caller's list
    }

    public List<String> getMembers() {
        return members; // hands out the real list
    }
}
