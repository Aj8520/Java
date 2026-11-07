package c10_why_exceptions;

import java.util.HashSet;
import java.util.Set;

/** Real-life example: the same rule written in error-code style and in exception style. */
public class SeatBooking {

    private static final int SEATS = 10;
    private final Set<Integer> booked = new HashSet<>();

    /** Error-code style: returns -1 for "invalid seat", -2 for "already taken". Easy to ignore. */
    public int bookWithCode(int seat) {
        if (seat < 1 || seat > SEATS) {
            return -1;
        }
        if (!booked.add(seat)) {
            return -2;
        }
        return seat;
    }

    /** Exception style: the normal path returns normally; problems can't be silently ignored. */
    public int book(int seat) {
        if (seat < 1 || seat > SEATS) {
            throw new IllegalArgumentException("seat must be 1-" + SEATS + ", was " + seat);
        }
        if (!booked.add(seat)) {
            throw new IllegalStateException("seat " + seat + " is already booked");
        }
        return seat;
    }
}
