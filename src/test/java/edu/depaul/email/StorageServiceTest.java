package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {
    String emailTest1 = "blahblahblah@yahoo.com";
    String linkTest1 = "http://www.facebook.com";
    String badLink1 = "http://www.somebadlinkthatprobablywontwork38374.com";

    @Test
    @DisplayName("this is the test to see if ss is an instance of StorageService")
    void testSetupStorage() {
        StorageService ss = new StorageService();
        assertTrue(ss instanceof StorageService);
        assertNotNull(ss);
    }
    @Test
    @DisplayName("test email when array is not empty")
    void testWriteEmail() {
        String fileName = "email.txt";
        try {
            new FileOutputStream(fileName).close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StorageService ss = new StorageService();
        Collection<String> c = new ArrayList<String>();
        c.add(emailTest1);
        ss.addLocation(StorageService.StorageType.EMAIL, fileName);
        ss.storeList(StorageService.StorageType.EMAIL, c);
        try {
            List<String> l = Files.readAllLines(Paths.get(fileName));
            assertEquals(c, l);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test good link when array is not empty")
    void testWriteGoodLink() {
        String fileName = "good-links.txt";
        try {
            new FileOutputStream(fileName).close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StorageService ss = new StorageService();
        Collection<String> c = new ArrayList<String>();
        c.add(linkTest1);
        ss.addLocation(StorageService.StorageType.GOODLINKS, fileName);
        ss.storeList(StorageService.StorageType.GOODLINKS, c);
        try {
            List<String> l = Files.readAllLines(Paths.get(fileName));
            assertEquals(c, l);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test write bad link when array is not empty")
    void testWriteBadLink() {
        String fileName = "badlinks.txt";
        try {
            new FileOutputStream(fileName).close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        StorageService ss = new StorageService();
        Collection<String> c = new ArrayList<String>();
        c.add(badLink1);
        ss.addLocation(StorageService.StorageType.BADLINKS, fileName);
        ss.storeList(StorageService.StorageType.BADLINKS, c);
        try {
            List<String> l = Files.readAllLines(Paths.get(fileName));
            assertEquals(c, l);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test storage when email array is empty")
    void testEmptyEmail() {
        StorageService ss = new StorageService();
        Collection<String> c= new ArrayList<String>();
        assertThrows(EmailFinderException.class, () -> ss.storeList(StorageService.StorageType.EMAIL, c));
    }
    @Test
    @DisplayName("Empty array Test on good links")
    void emptyGoodLinksTest() {
        StorageService ss = new StorageService();
        Collection<String> c = new ArrayList<String>();
        assertThrows(EmailFinderException.class, () -> ss.storeList(StorageService.StorageType.GOODLINKS, c));
    }
    @Test
    @DisplayName("Empty array Test on bad links")
    void emptyBadLinksTest() {
        StorageService ss = new StorageService();
        Collection<String> c = new ArrayList<String>();
        assertThrows(EmailFinderException.class, () -> ss.storeList(StorageService.StorageType.BADLINKS, c));
    }


}