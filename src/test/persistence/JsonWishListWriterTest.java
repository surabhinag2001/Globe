package persistence;

import model.WishList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWishListWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WishList wl = new WishList();
            JsonWishListWriter writer = new JsonWishListWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            //pass
        }
    }
    
}
