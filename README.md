
# Media Tracking System Final Project

Group T - Alyssa Schaefer, Thomas Quan, Evie Wilbur, Wen-Hsin Chen
This project will be a media tracking system for songs and playlists. It will have a user login system so that the user can keep track of their favorite tracks, create playlists, organize music events, determine user preferences, recommend songs based on preferences, and more!  

Check out a video demonstration [(here)](https://www.canva.com/design/DAGEGlQaZLc/bhi7g7ajQ36lW7NE7DC31Q/watch?utm_content=DAGEGlQaZLc&utm_campaign=designshare&utm_medium=link&utm_source=editor)

### Navigating the program:
Once a command is received from the console, the switch statement in UserInterface will go to the execution method in UIExecution. UIExecution has execution methods which you can use to start navigating through the methods that make that feature work.

## Login information

> username = 'test'

> password = 'test' 


## Alyssa Schaefer features

### Login System
  **Description:**  This feature takes a given username and password and checks if there is an account stored in the system that matches these parameters. If so, it returns the Account object and logs the user in.<br>
  **Package:** edu.mu.finalproject.controller<br>
  **Class:** AccountController<br>
  **Method:** public Account loginUser(String username, String password);

### Account Creation
  **Description:**  This feature takes a given username and password and uses them to create a new Account object. It then saves this in the system and logs the user in if the creation is successful.<br>
  **Package:** edu.mu.finalproject.controller<br>
  **Class:** AccountController<br>
  **Method:** public Account createAccount(String username, String password);

### Editing Account Information
  **Description:**  This feature allows logged in users to change their account username or password.<br>
  **Package:** edu.mu.finalproject.controller<br>
  **Class:** AccountController<br>
  **Username Method:** public int changeUsername(String newUsername, oldUsername);<br>
  **Password Method:** public int changePassword(String username, String oldPassword, String newPassword);

### Follow Other Users
  **Description:** This feature allows users in the system to follow other users.<br>
  **Package:** edu.mu.finalproject.controller<br>
  **Class:** AccountController<br>
  **Method:** public int followUser(String username, String userToFollow);

### Save Songs and Playlists to Account
  **Description:**  This feature allows users to save the names of a song or playlist in the system to the their account.<br>
  **Package:** edu.mu.finalproject.controller<br>
  **Class:** AccountController<br>
  **Song Method:** public int saveSong(String song, String username);<br>
  **Playlist Method:** public int savePlaylist(String playlist, String username);


## Thomas Quan features

Add Media Items
Description: Adds a new media item to the media catalog.
Package: edu.mu.finalproject.controller
Class: MediaCatalogController
Method: public static boolean addMedia(MediaProduct mediaProduct)
Helper methods:
public static Boolean addMedia(MediaProduct mediaProduct) {

Delete Media Items
Description: Deletes a media item from the media catalog by index.
Package: edu.mu.finalproject.controller
Class: MediaCatalogController
Method: public static boolean deleteMedia(int index)
Helper methods:
public static Boolean deleteMedia(int id) 

Display All Media Items
Description: Displays all media items currently stored in the media catalog.
Package: edu.mu.finalproject.controller
Class: MediaCatalogController
Method: public static Boolean displayAll()
Helper methods:
public static Boolean displayAll() 

Display Shuffle Media Items
Description: Displays all media items in a shuffled order, providing a randomized view of the media catalog.
Package: edu.mu.finalproject.controller
Class: MediaCatalogController
Method: public static Boolean displayShuffle()
Helper methods:
public static Boolean displayShuffle() {


Create Catalog File
Description: Creates a file containing all media items, allowing the user to export their media catalog.
Package: edu.mu.finalproject.controller
Class: MediaCatalogController
Method: public Boolean saveCatalogToFile(String filename)
Helper methods:

private String formatMediaForFile(MediaProduct mediaProduct): Formats a single media item for file writing.
public Boolean loadMediaFromFiles(List<? extends MediaProduct> products) {
## Evie Wilbur features

### Mark Something As Favorite
  **Description:** Favorites or unfavorites a media product
  **Package:** edu.mu.finalproject.controller
  **Class:** FavoritesController
  **Method:** public int favorite(MediaProduct objectToBeFavorited)
  Helper methods:
  public int displayFavoriteResult(MediaProduct objectToBeFavorited)
  public int toggleFavorite()

### View Favorites Summary
  **Description:** Asks which type you would like to see a favorite summary of. Displays the number of favorited items and names of favorited items of that type.
  **Package:** edu.mu.finalproject.view
  **Class:** FavoritesView
  **Method:** public ArrayList <? Extends MediaProduct> displayFavoritesInfo(ArrayList <? extends MediaObject> favorites)
  Helper methods:
  public String getFavInfo()
  public ArrayList <MediaObject> gatherFavorited(Class<? extends MediaObject> type, ArrayList<MediaProduct> catalog)


### Add Event
  **Description:** Adds an event to the event catalog.
  **Package:** edu.mu.finalproject.controller
  **Class:** EventController
  **Method:** public static Event addEvent(Event myEvent)
  Helper methods:
  public static int viewAddEvent(Event myEvent)
  public static int appendEvent(Event myEvent): 

### Delete Event
  **Description:** Deletes an event from the event catalog. 
  **Package:** edu.mu.finalproject.controller
  **Class:** EventController
  **Method:** public static int deleteEvent(Event eventToBeDeleted)
  Helper methods
  public static int viewDeleteEvent(Event evetToBeDeleted)
  public static int deleteEventFromFile(MonthDay monthDate, Year year, String artistName)

### Display Events		
  **Description:** Gets events to display based on the date range specified by the user.
  **Package:** edu.mufinalproject.view
  **Class:** EventView
  **Method:** public ArrayList <Event> getEventsToDisplayByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate): 
  Helper methods:
  public int getEventDisplayInfo()
  public int displayEventsByDate(ArrayList <Event> eventsInRange)

*Note: The events.json has been prepopulated with example events to make seeing how the event features work easier at first.*

## Wen-Hsin Chen

### Search

Searches through CatalogSingleton's ArrayList<MediaProduct> for a MediaProduct (can be Song, Playlist, Artist). Displays topNResults from CatalogSingleton's ArrayList<MediaProduct> sorted by most to least matching to queryStr. Returns false if no match is found.

### Manual Setup Preference

Prompts user to set their own preference by displaying list of preferences  for user to choose from. Returns the preference matching user’s answer.

### Quiz Setup Preference

Fetches quiz questions from a json file, displays it to user & prompts for user’s answers. User’s answers are mapped to a “scoreboard” that tracks how many answers are matched to each Preference enum. The Preference enum with the most matched score will be returned

### Create & Display Recommendation Playlist

Gets the Preference field stored in passed-in Account argument. If no preference for account, call setupPreference(), else filter CatalogSingleton's ArrayList<MediaProduct> for songs that match Account’s preference. Create a Playlist & add the filtered songs into playlist. Return created Playlist.

### Download Recommended Playlist

Gets & downloads account’s recommended playlist and format’s the playlist’s fields into a html file (html formatting read from a html template). Songs in the playlist are displayed via beautiful html flipcards that can be flipped around to reveal a youtube link button that’ll query for the song’s name on youtube’s search bar. The photos in html file will use song’s imgDescription to query & fetch for background images on unsplash.com. Returns false if download fails/if Account doesn’t have a preference

# Class diagram

We tried to put in 1 diagram but had to split it up due to plantUML not rendering. 
![pLbjS-Es4VxENu4gCorPsMxxLKxTw2ovKMhPhgIxDfF9U70aB3D74Ye0dK-vNdzxiI2m0acGkfjaElMCJNBtsSMp2s3nuc-aeaBjooBwZNfc9IEfKEUqVCj49D_brOuAMfAqe59EbHBvsxrYYvHNw_ndHluOHKP3bZIloCU8w9zngTzZAhQIJCXQMrJR7tvCzFjxdcVH9sVnUZ7_mDAzod](https://github.com/23wc01/T-CS3330-FinalProject/assets/132469274/91f45ff5-9668-4cab-a96b-dcf7030a07da)

![class diagram](https://github.com/23wc01/T-CS3330-FinalProject/assets/132469274/f220b5e5-0de6-4445-8176-47eef68cc782)
