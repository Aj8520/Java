package c12_checked_vs_unchecked;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/** The handbook's three ways to deal with a checked exception. */
public class ConfigReader {

    /** 1. Declare it: "callers, deal with it". */
    public String readConfig(Path p) throws IOException {
        return Files.readString(p);
    }

    /** 2. Handle it here, or 3. wrap it in an unchecked exception and rethrow. */
    public String readConfigOrDefault(Path p) {
        try {
            return Files.readString(p);
        } catch (NoSuchFileException e) {
            return "{}"; // a missing file is fine: use defaults
        } catch (IOException e) {
            throw new UncheckedIOException("Can't read " + p, e); // anything else: wrap
        }
    }

    // public String broken(Path p) { return Files.readString(p); } // compile error: unreported IOException
}
