package edu.depaul.email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.IOException;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ListWriterTest {
    String emailTest1 = "blahblahblah@yahoo.com";
    String emailTest2 = "shdhfhf@gmail.com";
    String emailTest3 = "djfufu@aol.com";
    String linkTest1 = "http://www.facebook.com";
    String linkTest2 = "http://www.youtube.com";
    String linkTest3 = "http://www.google.com";
    String badLink1 = "http://www.somebadlinkthatprobablywontwork38374.com";
    String badLink2 = "http://www.idkidkdikd1937ddjdh23.com";
    String badLink3 = "http://www.de445rdswcndh3fgg.com";

    @Test
    @DisplayName("Test the constructor of list writer class")
    void constructorTest() {
        OutputStream os = null;
        try {
            os = new FileOutputStream("empty.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ListWriter lw = new ListWriter(os);
        assertNotNull(lw);
    }

    @Test
    @DisplayName("Test email writing function of list writer")
    void emailWriteTest() {
        OutputStream os = null;
        try {
            os = new FileOutputStream("email.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ListWriter lw = new ListWriter(os);
        Collection<String> c = new ArrayList<>();
        c.add(emailTest1);
        c.add(emailTest2);
        c.add(emailTest3);
        try {
            lw.writeList(c);
            BufferedReader br = new BufferedReader(new FileReader("email.txt"));
            String line = br.readLine();
            while (line != null) {
                for (String item : c) {
                    assertEquals(item, line);
                    line = br.readLine();
                }
            }
            br.close();
        }
        catch (IOException err) {
            throw new EmailFinderException("Error ", err);
        }
    }

    @Test
    @DisplayName("Test the write list function with good links")
    void writeListGoodLinksTest() {
        OutputStream os = null;
        try {
            os = new FileOutputStream("good-links.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ListWriter lw = new ListWriter(os);
        Collection<String> c = new ArrayList<>();
        c.add(linkTest1);
        c.add(linkTest2);
        c.add(linkTest3);
        try {
            lw.writeList(c);
            BufferedReader br = new BufferedReader(new FileReader("good-links.txt"));
            String line = br.readLine();
            while (line != null) {
                for (String item : c) {
                    assertEquals(item, line);
                    line = br.readLine();
                }
            }
            br.close();
        }
        catch (IOException err) {
            throw new EmailFinderException("Error ", err);
        }
    }

    @Test
    @DisplayName("Test the write list function with bad links")
    void writeListBadLinksTest() {
        OutputStream os = null;
        try {
            os = new FileOutputStream("badlinks.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ListWriter lw = new ListWriter(os);
        Collection<String> c = new ArrayList<>();
        c.add(badLink1);
        c.add(badLink2);
        c.add(badLink3);
        try {
            lw.writeList(c);
            BufferedReader br = new BufferedReader(new FileReader("badlinks.txt"));
            String line = br.readLine();
            while (line != null) {
                for (String item : c) {
                    assertEquals(item, line);
                    line = br.readLine();
                }
            }
            br.close();
        }
        catch (IOException err) {
            throw new EmailFinderException("Error ", err);
        }
    }
}
