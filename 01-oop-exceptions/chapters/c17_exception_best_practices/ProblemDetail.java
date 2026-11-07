package c17_exception_best_practices;

/** A plain-Java stand-in for Spring's ProblemDetail (RFC 9457 error body). */
public record ProblemDetail(int status, String title, String detail) { }
