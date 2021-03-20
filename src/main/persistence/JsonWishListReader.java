package persistence;


import exceptions.CountryAlreadyPresentException;
import exceptions.InvalidCountryException;
import model.WishList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads wishlist from JSON data stored in file
// Citation: code taken and modified from JsonReader class in JsonSerializationDemo
public class JsonWishListReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonWishListReader(String source) {
        this.source = source;
    }

    //EFFECTS:reads wishlist from file and returns it;
    //throws IOException if an error occurs reading data from file
    public WishList read() throws IOException, InvalidCountryException, CountryAlreadyPresentException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseWishList(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    // Citation: code taken from readFile method in JsonReader class in JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses wishlist from JSON array and returns it
    private WishList parseWishList(JSONArray jsonArray) throws InvalidCountryException,
            CountryAlreadyPresentException {
        WishList wl = new WishList();
        for (Object json : jsonArray) {
            JSONObject nextCountry = (JSONObject) json;
            addCountry(wl, nextCountry);
        }
        return wl;
    }

    // MODIFIES: wl
    // EFFECTS: parses wish country from JSON object and adds it to wishlist
    private void addCountry(WishList wl, JSONObject jsonObject) throws InvalidCountryException,
            CountryAlreadyPresentException {
        String name = jsonObject.getString("name");
        String notes = jsonObject.getString("notes");
        wl.addCountry(name,notes);
    }
}

