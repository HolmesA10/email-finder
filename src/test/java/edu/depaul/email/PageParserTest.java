package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PageParserTest {
    @Test
    @DisplayName("test constructor.")
    void constructorTest() {
        PageParser pp = new PageParser();
        assertNotNull(pp);
    }
    @Test
    @DisplayName("test when email isnt found in document")
    void noEmailFoundTest() {
        String html = "<html><body></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser pp = new PageParser();
        Set<String> emails = pp.findEmails(doc);
        assertEquals(0, emails.size());
    }
    @Test
    @DisplayName("Tests finding one email in a document.")
    void oneEmailFoundTest() {
        String email1 = "randomemail1@gmail.com";
        String html = "<html><body>" + email1 + "</body></html>";
        Document doc = Jsoup.parse(html);
        PageParser pp = new PageParser();
        Set<String> emails = pp.findEmails(doc);
        assertEquals(1, emails.size());
    }
    @Test
    @DisplayName("Tests finding multiple emails in a document.")
    void moreThanOneEmailFoundTest() {
        String email1 = "randomemail1@yahoo.com";
        String email2 = "randomemail2@gmail.com";
        String email3 = "randomemail3@aol.com";
        String html = "<html><body>" + email1 + ", " + email2 + ", " + email3 + "</body></html>";
        Document doc = Jsoup.parse(html);
        PageParser pp = new PageParser();
        Set<String> emails = pp.findEmails(doc);
        assertEquals(3, emails.size());
    }
    @Test
    @DisplayName("test out the page parser link finder with invalid link")
    void findInvalidLink() {
        String html = "<html><a href='/some/other/file.html'>some link right here will be written</a></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser test = new PageParser();
        test.findLinks(doc);
        assertEquals(null,doc.getElementById("some link right here will be written"));
    }
    @Test
    @DisplayName("test when link isnt found in document")
    void noLinkFoundTest() {
        String html = "<html><body></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser pp = new PageParser();
        Set<String> emails = pp.findLinks(doc);
        assertEquals(0, emails.size());
    }
    @Test
    @DisplayName("Tests finding one link in a document.")
    void oneLinkFoundTest() {
        String email1 = "<a href='http://www.facebook.com'> http://www.facebook.com </a>";
        String html = "<html><body>" + email1 + "</body></html>";
        Document doc = Jsoup.parse(html);
        PageParser pp = new PageParser();
        Set<String> emails = pp.findLinks(doc);
        assertEquals(1, emails.size());
    }

    @Test
    @DisplayName("Tests finding multiple links in a document.")
    void MoreThanOneEmailFoundTest() {
        String email1 = "<a href='http://www.youtube.com'> http://www.youtube.com </a>";
        String email2 = "<a href='http://www.facebook.com'> http://www.facebook.com </a>";
        String email3 = "<a href='www.google.com'> www.google.com </a>";
        String html = "<html><body>" + email1 + ", " + email2 + ", " + email3 + "</body></html>";
        Document doc = Jsoup.parse(html);
        PageParser pp = new PageParser();
        Set<String> emails = pp.findLinks(doc);
        assertEquals(3, emails.size());
    }
}
