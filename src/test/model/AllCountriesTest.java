package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//class to test functions defined in AllCounties class
public class AllCountriesTest {

    @Test
    public void testCheckExists() {
        AllCountries obj = new AllCountries();
        assertTrue(obj.getAllCountries().contains("India".toUpperCase()));
        assertTrue(obj.getAllCountries().contains("United Kingdom".toUpperCase()));
        assertFalse(obj.getAllCountries().contains("neverland".toUpperCase()));
    }

}
