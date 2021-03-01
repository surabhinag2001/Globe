package persistence;

import model.WishCountryTest;
import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//class to test functions defined in JsonWishListReader class
// Citation: code taken and modified from JsonWriterTest class in JsonSerializationDemo
public class JsonWishListReaderTest extends WishCountryTest {

    @Test
    void testReaderNonExistentFile() {
        JsonWishListReader reader = new JsonWishListReader("./data/noSuchFile.json");
        try {
            WishList wl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyWishList() {
        JsonWishListReader reader = new JsonWishListReader("./data/testReaderEmptyWishlist.json");
        try {
            WishList wl = reader.read();
            assertEquals(0,wl.getMyWishList().size());
        } catch (IOException e) {
            System.out.println("Couldn't read from file");
        }
    }

    @Test
    void testGeneralWishList() {
        JsonWishListReader reader = new JsonWishListReader("./data/testReaderGeneralWishlist.json");
        try {
            WishList wl = reader.read();
            assertEquals(2,wl.getMyWishList().size());
            assertEquals("Thailand",wl.getMyWishList().get(0).getCountryName());
            assertEquals("thaifood",wl.getMyWishList().get(0).getNotesCountry());
            assertEquals("Germany",wl.getMyWishList().get(1).getCountryName());
            assertEquals("foosball",wl.getMyWishList().get(1).getNotesCountry());
        } catch (IOException e) {
            System.out.println("Couldn't read from file");
        }
    }

}
