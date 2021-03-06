package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

//class representing all countries of the world
public class AllCountries {
    private final List<String> countries;

    public AllCountries() {
        countries = new LinkedList<>();
        String[] locales = Locale.getISOCountries();
        for (String country : locales) {
            Locale obj = new Locale("", country);
            countries.add(obj.getDisplayCountry().toUpperCase());
        }
    }

    //EFFECTS: displays all countries
    public List<String> getAllCountries() {
        return countries;
    }
}
