package c13_try_catch_finally;

/** Real-life example: a room must be released even if the meeting goes wrong. */
public class MeetingRoom {

    private boolean occupied;

    public void book(String who) {
        if (occupied) {
            throw new IllegalStateException("room already occupied");
        }
        occupied = true;
        System.out.println("  " + who + " booked the room");
    }

    public void release() {
        occupied = false;
        System.out.println("  room released");
    }

    public boolean isOccupied() {
        return occupied;
    }
}
