package edu.depaul.email;
        import org.junit.jupiter.api.DisplayName;
        import static org.junit.jupiter.api.Assertions.assertEquals;
        import org.junit.jupiter.api.Test;
        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;

public class EmailFinderTest {

    @Test
    @DisplayName("Check email.txt for good emails")
    void testEmailFile()
    {
        String[] link={"http://www.depaul.cdm.com"};
        EmailFinder ef = new EmailFinder();
        ef.run(link);
    }

    @Test
    @DisplayName("test out the page parser")
    void testPageParser() {
        String html = "<html><a href='/some/other/file.html'>some link right here will be written</a></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser test = new PageParser();
        test.findLinks(doc);
        assertEquals(null,doc.getElementById("some link right here will be written"));
    }

}