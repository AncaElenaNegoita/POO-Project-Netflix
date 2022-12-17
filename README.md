# POO-Project-Netflix

TV App(like Netflix) is a program that lets people choose
and watch movies available in their country, interacting
with them in order to keep track of the most liked and rated
movies. The base needed to be implemented in order to be functional
for each individual command a user can make and to have a
suggestive output for the outcome of an interaction.

The first step in this project was creating the input package
that has all the necessary resources for reading the file
that has all the data. The input file found in the first argument
of the string array from main consists of an array of users,
an array of movies and an array of actions. Each user has
assosiated with his account a name, password, country, account
type(premium or standard), balance(his real life money),
tokens, number of free movies for the premium user and lists
of purchased, watched, liked and rated movies. The country
is needed in order to verify which movies are available
for the user and the balance to buy tokens, that are the only
currency for the app and can be used to buy a premium
account or movies. Each movie has a name, duration, rating,
likes, and a list consisted of countries that can't see the
movie (because they are banned for them). 

The array of actions is essential for the program because
it has all the features from the input file that are verified
and then applied when the outcome is successful. A user can
change a page or do an action on the current page. All features
are implemented using a map that has a AllFeaturesEnum as the key,
and each key has a lambda function assosiated with each one of them
that creates a new instance for each class that has the same name
as its key. In order to make the class easier to be accessed and its
instance to be created only once, the Singleton Design Pattern
comes in handy, and in order to create each function for the specific class
in the map using the constructor, the Factory Design Pattern was used.
This class is used in ActionsChangePageAndOnPage where, with the help 
of the State Design Pattern, each state of a page(class) is created and
each page points to another state of a page(class) and can access only them.
Also, each page has its possible feauters implemented and can be accesed only
when the user is on that page. The variable currentPage from UserActions
stores the state of the current page that is returned when calling
the functions onPage and changePage from the state class.

In changePage, 3 conditions are verified first:
- if the given page is _logout_, then the current user is emptied
and the currentPage becomes the unauthenticated one.
- if the given page is _movies_, then the filteredMovies array is
implemented and will have only the film that are not banned in the user's
country. Also, each film is copied and inserted in this array using the Prototype
Design Pattern.
- if the given page is _see details_, then the movie from action
is searched in the filtered movie list and, if found, is inserted
- in the array after it is emptied
- for the other pages, it searches if it has that page assosiated and returns
the state of the next page and, on the other case, an error

In onPage, each feature was implemented in its class and is called when found in the
map of the state. Each one of them returns the state of the current page when 
successful(except login and register), or an error. 
- in login, the user authenticates if its credentials exist in the user array from input
- in register, the user authenticates and its credentials are added in the array of users
- in movies, a user can search movies that start with a string, or filter a list.
Also, the movies need to be available in the country of the user.
- in see details, a user can purchase, watch, like and rate a movie. He can only watch
if he has purchased it (or has premium account, thus he has 15 free movies). He can like
and rate if he has watched the movie.
- in upgrades, the user can buy a premium account or tokens because this is the currency on the app

