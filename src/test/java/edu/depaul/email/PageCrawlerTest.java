package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.depaul.email.StorageService.StorageType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PageCrawlerTest {
    @Test
    @DisplayName("test page crawler constructor")
    void testConstructor() {
        StorageService ss = mock(StorageService.class);
        PageCrawler pc = new PageCrawler(ss);
        assertNotNull(pc);
    }
    @Test
    @DisplayName("test the max size of constructor")
    void testConstructorMaxSize() {
        StorageService ss = mock(StorageService.class);
        PageCrawler pc = new PageCrawler(ss, 50);
        assertNotNull(pc);
    }
    @Test
    @DisplayName("test one link and one email crawl")
    void oneCrawlTest() {
        StorageService ss = new StorageService();
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        PageCrawler pc = new PageCrawler(ss, 5);
        pc.crawl("src/test/resources/someGoodLinks.txt");
        assertEquals(pc.getEmails().size(), 1);
    }
    @Test
    @DisplayName("test more than one emails crawl")
    void multipleCrawlTest() {
        StorageService ss = new StorageService();
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        PageCrawler pc = new PageCrawler(ss, 5);
        pc.crawl("src/test/resources/multipleEmails.txt");
        assertEquals(pc.getEmails().size(), 3);
    }
    @Test
    @DisplayName("test when its more than the max boundary")
    void maxCrawlTest() {
        StorageService ss = new StorageService();
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        PageCrawler pc = new PageCrawler(ss, 5);
        pc.crawl("src/test/resources/boundary.txt");
        assertFalse(pc.getEmails().size() == 5);
    }
    @Test
    @DisplayName("test with no email crawl")
    void noEmailCrawlTest() {
        StorageService ss = new StorageService();
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        PageCrawler pc = new PageCrawler(ss, 5);
        pc.crawl("src/test/resources/noEmail.txt");
        assertEquals(pc.getEmails().size(), 0);
    }
    @Test
    @DisplayName("test with a file that does not exist and good link")
    void noFileGoodLinkCrawlTest() {
        StorageService ss = mock(StorageService.class);
        PageCrawler pc = new PageCrawler(ss);
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        pc.crawl("thisfiledoesntexist.txt");
        assertEquals(pc.getGoodLinks().size(), 0);
    }
    @Test
    @DisplayName("test with a file that exists and 1 good link")
    void oneGoodLinkCrawlTest() {
        StorageService ss = mock(StorageService.class);
        PageCrawler pc = new PageCrawler(ss);
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        pc.crawl("src/test/resources/someGoodLinks.txt");
        assertEquals(pc.getGoodLinks().size(), 1);
    }
    @Test
    @DisplayName("test with a file that has nothing for bad link")
    void emptyBadLinkCrawlTest() {
        StorageService ss = mock(StorageService.class);
        PageCrawler pc = new PageCrawler(ss);
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        pc.crawl("src/test/resources/badlinks.txt");
        assertEquals(pc.getBadLinks().size(), 0);
    }
    @Test
    @DisplayName("test with a file that has multiple bad link")
    void threeBadLinkCrawlTest() {
        StorageService ss = mock(StorageService.class);
        PageCrawler pc = new PageCrawler(ss);
        ss.addLocation(EMAIL, "email.txt");
        ss.addLocation(GOODLINKS, "good-links.txt");
        ss.addLocation(BADLINKS, "badlinks.txt");
        pc.crawl("src/test/resources/badlinkcrawl.txt");
        assertEquals(pc.getBadLinks().size(), 3);
    }


}
