import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimestampExample {
    public static void main(String[] args) {
        // Define a sample date string in Twitter's timestamp format
        String dateString = "Wed Oct 10 20:19:24 +0000 2018";
        
        // Define the formatter to parse the timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        
        // Parse the date string to a ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
        
        // Display the parsed ZonedDateTime
        System.out.println("Parsed ZonedDateTime: " + zonedDateTime);
        
        // Convert to an Instant (useful for UTC time representation)
        Instant instant = zonedDateTime.toInstant();
        System.out.println("Instant (UTC): " + instant);
        
        // Format the ZonedDateTime to a different string format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedDate = zonedDateTime.format(outputFormatter);
        System.out.println("Formatted ZonedDateTime: " + formattedDate);
        
        // Convert the Instant back to a ZonedDateTime in a specific time zone (e.g., New York)
        ZonedDateTime newYorkTime = instant.atZone(ZoneId.of("America/New_York"));
        System.out.println("ZonedDateTime in New York: " + newYorkTime);
        
        // Output the time in epoch seconds (Unix time)
        System.out.println("Epoch seconds: " + instant.getEpochSecond());
    }
}