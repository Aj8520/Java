package c16_custom_exceptions;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * 01.16 · Custom exceptions and chaining
 * Handbook: OOP, Exceptions & Modern Java (oop.html#custom)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c16_custom_exceptions.Practice
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Custom exceptions ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): the handbook's three custom exceptions.
    static void coreExample() {
        System.out.println("\n-- 1. Checked, unchecked, chained --");
        try {
            withdraw(new BigDecimal("500.00"), new BigDecimal("750.50"));
        } catch (InsufficientFundsException e) {
            System.out.println("checked:   " + e.getMessage() + ", short by " + e.getShortBy());
        }

        try {
            findOrder(42);
        } catch (OrderNotFoundException e) {
            System.out.println("unchecked: " + e.getMessage());
        }

        try {
            saveOrder(7);
        } catch (OrderPersistenceException e) {
            System.out.println("chained:   " + e.getMessage());
            System.out.println("  caused by " + e.getCause().getClass().getSimpleName() + ": " + e.getCause().getMessage());
        }
    }

    static void withdraw(BigDecimal balance, BigDecimal amount) throws InsufficientFundsException {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientFundsException(balance, amount);
        }
    }

    static void findOrder(long id) {
        throw new OrderNotFoundException(id);
    }

    static void saveOrder(long id) {
        try {
            fakeRepositorySave(id);
        } catch (SQLException e) {
            throw new OrderPersistenceException("Could not save order " + id, e); // pass e!
        }
    }

    static void fakeRepositorySave(long id) throws SQLException {
        throw new SQLException("duplicate key value violates unique constraint \"orders_pkey\"", "23505");
    }

    // TODO 2 (done): three ways to wrap. Predict getMessage() and getCause() BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        SQLException low = new SQLException("connection refused");

        RuntimeException lost = new RuntimeException("save failed");               // cause forgotten
        RuntimeException kept = new RuntimeException("save failed", low);         // message + cause
        RuntimeException onlyCause = new RuntimeException(low);                   // cause only

        System.out.println("lost:      message=" + lost.getMessage() + ", cause=" + lost.getCause());
        System.out.println("kept:      message=" + kept.getMessage() + ", cause=" + kept.getCause().getMessage());
        System.out.println("onlyCause: message=" + onlyCause.getMessage());

        RuntimeException later = new RuntimeException("added later");
        later.initCause(low); // for exceptions with no (message, cause) constructor
        System.out.println("initCause: cause=" + later.getCause().getMessage());
    }

    // TODO 3 (done): my own real-life example.
    static final Map<String, LocalDate> LOANS = Map.of("Clean Code", LocalDate.of(2026, 11, 20));
    static final Set<String> CATALOGUE = Set.of("Clean Code", "Effective Java");

    static String borrow(String title) {
        if (!CATALOGUE.contains(title)) {
            throw new BookNotFoundException(title);
        }
        if (LOANS.containsKey(title)) {
            throw new BookOnLoanException(title, LOANS.get(title));
        }
        return "You borrowed '" + title + "'";
    }

    static void realLifeExample() {
        System.out.println("\n-- 3. Library --");
        for (String title : new String[] {"Effective Java", "Clean Code", "Harry Potter"}) {
            try {
                System.out.println(borrow(title));
            } catch (BookOnLoanException e) {
                System.out.println(e.getCode() + ": " + e.getMessage() + " -> reserve it for " + e.getDueBack());
            } catch (LibraryException e) { // the base type catches every other library problem
                System.out.println(e.getCode() + ": " + e.getMessage());
            }
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: "How would one handler turn many custom exceptions into the right HTTP status?"
    static int httpStatus(RuntimeException e) {
        if (e instanceof LibraryException le) {
            return switch (le.getCode()) { // switch expression (Java 14): every enum value covered
                case BOOK_NOT_FOUND -> 404;
                case BOOK_ON_LOAN -> 409;
                case MEMBER_BLOCKED -> 403;
            };
        }
        return 500; // anything unexpected
    }

    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        RuntimeException[] errors = {
            new BookNotFoundException("Dune"),
            new BookOnLoanException("Clean Code", LocalDate.of(2026, 11, 20)),
            new IllegalStateException("bug")
        };
        for (RuntimeException e : errors) {
            System.out.println(httpStatus(e) + " <- " + e.getClass().getSimpleName());
        }
    }
}
