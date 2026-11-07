package c08_composition_casting;

import java.util.ArrayList;
import java.util.List;

/**
 * Real-life example of the UML words.
 * Composition: a House CREATES and owns its Rooms; they don't exist without it.
 * Aggregation: a Team USES Players that already exist and outlive the team.
 */
public class House {
    private final List<Room> rooms = new ArrayList<>();

    public House(String... roomNames) {
        for (String name : roomNames) {
            rooms.add(new Room(name)); // created inside: lifetimes tied together
        }
    }

    public int roomCount() { return rooms.size(); }

    static class Room {
        private final String name;
        Room(String name) { this.name = name; }
        @Override public String toString() { return name; }
    }

    @Override
    public String toString() { return "House" + rooms; }
}

class Player {
    private final String name;
    Player(String name) { this.name = name; }
    @Override public String toString() { return name; }
}

class Team {
    private final List<Player> players;

    Team(List<Player> players) {
        this.players = List.copyOf(players); // given from outside: the players live on
    }

    @Override
    public String toString() { return "Team" + players; }
}
