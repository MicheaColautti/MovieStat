import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple logger utility that writes log messages to a file with timestamps.
 * This class ensures that the log folder exists before writing logs.
 *
 * @author Michea Colautti
 * @author Julian Cummaudo
 * @version 2025-02-21
 */
public class Logger {
    private static final String LOG_FOLDER = "log";
    private static final String LOG_FILE = LOG_FOLDER + "/app.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a message with a timestamp to the log file.
     *
     * @param message The message to be logged.
     */
    public void log(String message) {
        ensureLogFolderExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            writer.write(timestamp + " - " + message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    /**
     * Ensures that the log folder exists. If it does not exist, it creates the folder.
     */
    private static void ensureLogFolderExists() {
        File folder = new File(LOG_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
