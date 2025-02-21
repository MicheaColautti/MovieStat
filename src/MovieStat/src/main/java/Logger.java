import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FOLDER = "log";
    private static final String LOG_FILE = LOG_FOLDER + "/app.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

    private static void ensureLogFolderExists() {
        File folder = new File(LOG_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
