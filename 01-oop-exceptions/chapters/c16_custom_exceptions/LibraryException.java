package c16_custom_exceptions;

import java.time.LocalDate;

/**
 * Real-life example: a small exception HIERARCHY for a library app.
 * One base type with an error code; specific subclasses carry their own details.
 */
public abstract class LibraryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public enum Code { BOOK_NOT_FOUND, BOOK_ON_LOAN, MEMBER_BLOCKED }

    private final Code code;

    protected LibraryException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}

class BookNotFoundException extends LibraryException {
    private static final long serialVersionUID = 1L;

    BookNotFoundException(String title) {
        super(Code.BOOK_NOT_FOUND, "No book titled '" + title + "'");
    }
}

class BookOnLoanException extends LibraryException {
    private static final long serialVersionUID = 1L;

    private final LocalDate dueBack;

    BookOnLoanException(String title, LocalDate dueBack) {
        super(Code.BOOK_ON_LOAN, "'" + title + "' is on loan until " + dueBack);
        this.dueBack = dueBack;
    }

    LocalDate getDueBack() {
        return dueBack;
    }
}
