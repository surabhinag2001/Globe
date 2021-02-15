package ui;


import model.VisitedList;
import model.WishList;

import java.util.Scanner;

public class GlobeApp {
    private WishList wishCountries;
    private VisitedList visitedCountries;
    private final Scanner input = new Scanner(System.in);

    //EFFECTS: runs the globe application
    public GlobeApp() {
        runGlobe();
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGlobe() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            switch (command) {
                case ("q"):
                    keepGoing = false;
                    break;
                case "w":
                    handleWishlist();
                    break;
                case "v":
                    handleVisitedList();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for visited list
    private void handleVisitedList() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayVisitedMenu();
            command = input.next();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToVisitedList();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromVisitedList();
            } else if (command.equalsIgnoreCase("s")) {
                viewVisitedList();
            } else if (command.equalsIgnoreCase("f")) {
                filter();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }
    }


    //REQUIRES: dates are given in mm-yyyy format
    //EFFECT: displays countries that lie between specified dates
    private void filter() {
        System.out.println("Enter lower limit date: ");
        String minDate = input.next();
        input.nextLine();
        System.out.println("Enter upper limit date: ");
        String maxDate = input.next();
        input.nextLine();
        System.out.println("Countries visited between " + minDate + "and" + maxDate + " (inclusive) :");
        int size = visitedCountries.filterDateWise(minDate,maxDate).size();
        for (int j = 0; j < size; j++) {
            System.out.println(visitedCountries.filterDateWise(minDate,maxDate).get(j).getVisitedCountryName());
            System.out.println(visitedCountries.filterDateWise(minDate,maxDate).get(j).getDateVisited());
            System.out.println();
        }
    }

    //EFFECTS : displays list of all visited countries
    private void viewVisitedList() {
        for (int i = 0; i < visitedCountries.getMyVisitedList().size(); i++) {
            System.out.println(visitedCountries.getMyVisitedList().get(i).getVisitedCountryName());
            System.out.println(visitedCountries.getMyVisitedList().get(i).getDateVisited());
            System.out.println();
        }
    }

    //REQUIRES : date is entered in mm-yyyy format
    //MODIFIES: this
    //EFFECT : adds a country to the visited list
    private void addToVisitedList() {
        System.out.println("add to visited list accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine();
//        input.nextLine();
        System.out.print("Enter notes: ");
        String notes = input.nextLine();
//        input.nextLine();
        System.out.print("Enter date visited in mm-yyyy format: ");
        String date = input.nextLine();
        visitedCountries.addCountry(country, notes, date);
        System.out.println(country + " added to the wishlist with notes : " + notes + " date :" + date);
    }


    //REQUIRES : country being removed is present in the wishlist
    //MODIFIES: this
    //EFFECT : removes country from the visited list
    private void removeFromVisitedList() {
        System.out.println("remove from visited list accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine();
        visitedCountries.removeCountry(country);
        System.out.println(country + " removed from the visited list");
    }


    // MODIFIES: this
    // EFFECTS: processes user input for wish list
    private void handleWishlist() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayWishlistMenu();
            command = input.next();

            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else if (command.equalsIgnoreCase("a")) {
                addToWishlist();
            } else if (command.equalsIgnoreCase("r")) {
                removeFromWishList();
            } else if (command.equalsIgnoreCase("s")) {
                viewWishList();
            } else {
                System.out.println("Invalid selection");
                keepGoing = false;
            }
        }

    }

    //EFFECTS: displays wishlist
    private void viewWishList() {
        for (int i = 0; i < wishCountries.getMyWishList().size(); i++) {
            System.out.println(wishCountries.getMyWishList().get(i).getCountryName());
            System.out.println();
        }
    }


    //MODIFIES: this
    //EFFECT : adds a country to the wishlist
    private void addToWishlist() {
        System.out.println("add to wishlist accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine();
//        input.nextLine();
        System.out.print("Enter notes: ");
        String notes = input.nextLine();
        wishCountries.addCountry(country, notes);
        System.out.println(country + " added to the wishlist with notes : " + notes);
    }

    //REQUIRES : country being removes is present in the wishlist
    //MODIFIES: this
    //EFFECT : removes country from the wishlist
    private void removeFromWishList() {
        System.out.println("remove from wishlist accessed");
        System.out.print("Enter country name:");
        input.nextLine();
        String country = input.nextLine();
        wishCountries.removeCountry(country);
        System.out.println(country + " removed from the wishlist");
    }


    //MODIFIES: this
    //EFFECTS: initializes wishlist and visited lists
    public void init() {
        wishCountries = new WishList();
        visitedCountries = new VisitedList();
    }


    // EFFECTS: displays wishlist menu of options to user
    private void displayWishlistMenu() {
        System.out.println("wishlist menu displayed");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add country to wishlist");
        System.out.println("\tr -> Remove country from a wishlist ");
        System.out.println("\ts -> See names all countries in wishlist");
        System.out.println("\tq -> Quit Wishlist Menu");
    }

    // EFFECTS: displays visited menu of options to user
    private void displayVisitedMenu() {
        System.out.println("visited menu displayed");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add country to visited menu");
        System.out.println("\tr -> Remove a country from the visited menu");
        System.out.println("\ts -> See names and date of visit of all countries visited");
        System.out.println("\tf -> See names and date of visit of countries visited within specified dates");
        System.out.println("\tq -> Quit visited Menu");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> View wishlist menu");
        System.out.println("\tv -> View visited menu");
        System.out.println("\tq -> Quit main menu");
    }

}
