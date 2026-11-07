package c17_exception_best_practices;

import java.util.function.Supplier;

/**
 * Real-life example: what @RestControllerAdvice does in Spring Boot, written in plain Java.
 * Controllers just throw; ONE place turns exceptions into HTTP responses.
 * (Spring is not a dependency of this module, so this imitates it.)
 */
public class GlobalExceptionHandler {

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(long id) {
            super("Order " + id + " not found");
        }
    }

    public static int unexpectedLogged = 0;

    /** Like a DispatcherServlet calling a controller and then the matching @ExceptionHandler. */
    public static ProblemDetail call(Supplier<String> controller) {
        try {
            return new ProblemDetail(200, "OK", controller.get());
        } catch (OrderNotFoundException e) {
            return new ProblemDetail(404, "Not Found", e.getMessage());
        } catch (IllegalArgumentException e) {
            return new ProblemDetail(400, "Bad Request", e.getMessage());
        } catch (Exception e) { // last resort: log it once, hide the details from the client
            unexpectedLogged++;
            System.out.println("  [log.error] Unexpected error: " + e);
            return new ProblemDetail(500, "Internal Server Error", "Something went wrong");
        }
    }
}
