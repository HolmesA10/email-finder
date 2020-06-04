package edu.depaul.email;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PageFetcherTest {
    @Test
    @DisplayName("Tests PageFetcher constructor.")
    void testConstructor() {
        PageFetcher pf = new PageFetcher();
        assertNotNull(pf);
    }
    @Test
    @DisplayName("Test get on path/URL an empty string")
    void testGetEmptyStringPath() {
        PageFetcher pf = new PageFetcher();
        try {
            pf.get("");
        }
        catch (Exception e) {
            assertEquals(e.toString(), "edu.depaul.email.EmailFinderException: unable to fetch ");
        }
    }
    @Test
    @DisplayName("tests get() for online document")
    void testGetOnline() {
        PageFetcher pf = new PageFetcher();
        Document d = pf.get("http://www.depaul.edu");
        assertNotNull(d);
    }
    @Test
    @DisplayName("tests getString() for return string")
    void testGetStringForReturn() {
        PageFetcher pf = new PageFetcher();
        String s = pf.getString("http://www.facebook.com");
        assertNotNull(s);
    }
    @Test
    @DisplayName("tests get() for local document")
    void testGetLocalDoc() {
        PageFetcher pf = new PageFetcher();
        Document d = pf.get(System.getProperty("user.dir") + "\\src\\test\\resources\\testfile.html");
        assertNotNull(d);
    }
    @Test
    @DisplayName("tests get() for email exception")
    void testGetForException() {
        PageFetcher pf = new PageFetcher();
        String url = "http://www.idkwhatishouldwriteoverhere.com";
        assertThrows(EmailFinderException.class, () -> pf.get(url));
    }
    @Test
    @DisplayName("tests getString() for exception")
    void testGetStringForException() {
        PageFetcher pf = new PageFetcher();
        String url = "http://www.idkwhatishouldwriteoverhere.com";
        assertThrows(EmailFinderException.class, () -> pf.getString(url));
    }
}