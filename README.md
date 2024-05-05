# Media Tracking System Final Project
Group T - Alyssa Schaefer, Thomas Quan, Evie Wilbur, Wen-Hsin Chen

## Alyssa Schaefer features
Whatever you have in your individual feature document
## Thomas Quan features
Whatever you have in your individual feature document

## Evie Wilbur features
Mark Something As Favorite
  Description: Favorites or unfavorites a media product
  Package: edu.mu.finalproject.controller
  Class: FavoritesController
  Method: public int favorite(MediaProduct objectToBeFavorited)
  Helper methods:
  public int displayFavoriteResult(MediaProduct objectToBeFavorited)
  public int toggleFavorite()

View Favorites Summary
  Description: Asks which type you would like to see a favorite summary of. Displays the number of favorited items and names of favorited items of that type.
  Package: edu.mu.finalproject.view
  Class:FavoritesView
  Method: public ArrayList <? Extends MediaProduct> displayFavoritesInfo(ArrayList <? extends MediaObject> favorites)
  Helper methods:
  public String getFavInfo()
  public ArrayList <MediaObject> gatherFavorited(Class<? extends MediaObject> type, ArrayList<MediaProduct> catalog)


Add Event
  Description: Adds an event to the event catalog.
  Package: edu.mu.finalproject.controller
  Class: EventController
  Method: public static Event addEvent(Event myEvent)
  Helper methods:
  public static int viewAddEvent(Event myEvent)
  public static int appendEvent(Event myEvent): 

Delete Event
  Description: Deletes an event from the event catalog. 
  Package: edu.mu.finalproject.controller
  Class: EventController
  Method: public static int deleteEvent(Event eventToBeDeleted)
  Helper methods
  public static int viewDeleteEvent(Event evetToBeDeleted)
  public static int deleteEventFromFile(MonthDay monthDate, Year year, String artistName)

Display Events		
  Description: Gets events to display based on the date range specified by the user.
  Package: edu.mufinalproject.view
  Class: EventView
  Method:public ArrayList <Event> getEventsToDisplayByDate(Year startYear, MonthDay startDate, Year endYear, MonthDay endDate): 
  Helper methods:
  public int getEventDisplayInfo()
  public int displayEventsByDate(ArrayList <Event> eventsInRange)

 


## Wen-Hsin Chen features
Whatever you have in your individual feature document

# Class diagram
p5bjR-Eu4VxkNy7wW4A6axIVasrQhwrqVOrVQZlRBWu7Wv4OHxSIwPBqRdoBzBTtHYAbaIp5BHOeBi0cul2PuRmFfVsBiLpRGvRsVcUVHIPOfEqbpnw5xfdFYTnppJCMfToOaRKwUJnOCOsKN2U_2lR7NY_VOJEUIFQjn-1dcC5pmFNEi6ks1Wwv--dd0Jn_KKdSU_KS3qRegRH.svg
![pLbjS-Es4VxENu4gCorPsMxxLKxTw2ovKMhPhgIxDfF9U70aB3D74Ye0dK-vNdzxiI2m0acGkfjaElMCJNBtsSMp2s3nuc-aeaBjooBwZNfc9IEfKEUqVCj49D_brOuAMfAqe59EbHBvsxrYYvHNw_ndHluOHKP3bZIloCU8w9zngTzZAhQIJCXQMrJR7tvCzFjxdcVH9sVnUZ7_mDAzod](https://github.com/23wc01/T-CS3330-FinalProject/assets/132469274/e07717cf-7d46-4be6-83e2-a202c4a3878c)
