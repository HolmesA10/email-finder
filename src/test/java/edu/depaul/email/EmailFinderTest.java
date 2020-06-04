package edu.depaul.email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class EmailFinderTest {
    private static final String EMAIL_FILE = "email.txt";
    private static final String BADLINKS_FILE= "badlinks.txt";
    private static final String GOODLINKS_FILE = "good-links.txt";
    private static final String URL = "http://www.depaul.cdm.com";

    @Test
    @DisplayName("Test run() function by passing single argument")
    void singleArgumentTest() {
        String[] s = {URL};
        EmailFinder.main(s);
        File f1 = new File(BADLINKS_FILE);
        File f2 = new File(GOODLINKS_FILE);
        File f3 = new File(EMAIL_FILE);
        assertAll(
                () -> assertTrue(f1.exists(), "file "+BADLINKS_FILE + " cannot be found."),
                () -> assertTrue(f2.exists(), "file "+GOODLINKS_FILE + " cannot be found."),
                () -> assertTrue(f3.exists(), "file "+EMAIL_FILE + " cannot be found.")
        );
    }

    @Test
    @DisplayName("Test run() function by passing more than one argument")
    void testRunMultipleArgs() {
        String[] s = {URL,"1","3"};
        EmailFinder.main(s);
        File f1 = new File(BADLINKS_FILE);
        File f2 = new File(GOODLINKS_FILE);
        File f3 = new File(EMAIL_FILE);
        assertAll(
                () -> assertTrue(f1.exists(), "file "+BADLINKS_FILE + " cannot be found."),
                () -> assertTrue(f2.exists(), "file "+GOODLINKS_FILE + " cannot be found."),
                () -> assertTrue(f3.exists(), "file "+EMAIL_FILE + " cannot be found.")
        );
    }

    @Test
    @DisplayName("Test run() function by passing no argument")
    void testRunNoArgs() {
        String[] s = {};
        EmailFinder.main(s);
        File f1 = new File(BADLINKS_FILE);
        File f2 = new File(GOODLINKS_FILE);
        File f3 = new File(EMAIL_FILE);
        assertAll(
                () -> assertTrue(f1.exists(), "file "+BADLINKS_FILE + " cannot be found."),
                () -> assertTrue(f2.exists(), "file "+GOODLINKS_FILE + " cannot be found."),
                () -> assertTrue(f3.exists(), "file "+EMAIL_FILE + " cannot be found.")
        );
    }
    @Test
    @DisplayName("Test with empty url")
    void EmptyURLTest() {
        EmailFinder ef = new EmailFinder();
        String[] url = {};
        ef.run(url);
        String path = "good-links.txt";
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            assertEquals(content, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Invalid URL test run")
    void InvalidURLTest() {
        EmailFinder ef = new EmailFinder();
        String[] link = {"randominvalidurl"};
        ef.run(link);
        String fileName = "badlinks.txt";
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            assertEquals(content, "randominvalidurl");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Check email.txt for good emails")
    void testEmailFile()
    {
        String[] link={"http://www.depaul.cdm.com"};
        EmailFinder ef = new EmailFinder();
        ef.run(link);
    }



}
