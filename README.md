# Globe

## About this application 
Globe is an application that helps the user make a bucket-list of countries they want to visit and keep a 
track of all countries they have visited in the past. It also allows them to step back into time and view memories 
from all their travels!
## Why I want to build it 
I have always been passionate about exploring different countries and learning about new cultures, so I want to
build an application using which I can form of collection of destinations that I hope to visit, and the
ones that I have already visited. My means of this project I will also be able to practically apply what I am learning
in this course.

## User Stories 
#### Phase 1 :
- As a user, I want to be able to add countries to my wishlist.
- As a user, I want to be able to search a country by its name in my wishlist.
- As a user, I want to be able to remove countries from my wishlist.
- As a user, I want to be able to add countries to my 'visited' list.
- As a user, I want to be able to search a country by its name and date of visit in my 'visited' list. 
- As a user, I want to be able to remove a particular visit from my 'visited' list.
- As a user, I want to be able to add when (date) I visited a particular country.
- As a user, I want view what countries I visited during a given period.


#### Phase 2 :
- As a user, I want to be able to view a list of all countries of the world.
- As a user, I want to be able to automatically save my 'visited' list to file.
- As a user, I want to be able to automatically save my wishlist list to file.
- As a user, I want to be able to automatically load my 'visited' list from the file.
- As a user, I want to be able to automatically load my wishlist list from the file.


#### Phase 4 : Task 2
##### Robust class
VisitedList.addCountry throws three checked exceptions :
- InvalidCountryException
- FutureDateException
- CountryAlreadyPresentException
<br />
The tests checking these exceptions are present in VisitedListTest class.


#### Phase 4 : Task 3
##### Design refactoring
- Based on the UML diagram, an abstract class called AddDialogBox could be created.
- AddToVisitedDialogBox and AddToWishlistDialogBox would extend AddDialogBox.
- The methods common to AddToVisitedDialogBox and AddToWishlistDialogBox like setNotesUI, 
setNameUI, setErrorUI, setTextFieldUI, setAddButtonUI and display would have been declared in AddDialogBox
 to avoid duplication.
##### Non-Design refactoring
- GlobeApp.createTbPanel1, GlobeApp.createTbPanel2 and GlobeApp.createTbPanel3 have similar
  functionality and have duplicated lines of code, so an abstract method could have been created to
  avoid duplication.
