
# Media Tracking System Final Project

Group T - Alyssa Schaefer, Thomas Quan, Evie Wilbur, Wen-Hsin Chen
This project will be a media tracking system for songs and playlists. It will have a user login system so that the user can keep track of their favorite tracks, create playlists, organize music events, determine user preferences, recommend songs based on preferences, and more!  

Check out a video demonstration here: (link)

### Navigating the program:
Once a command is received from the console, the switch statement in UserInterface will go to the execution method in UIExecution. UIExecution has execution methods which you can use to start navigating through the methods that make that feature work.

## Login information

> username = 'test'

> password = 'test' 


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



## Wen-Hsin Chen
 features

Whatever you have in your individual feature document

# Class diagram

We tried to put in 1 diagram but had to split it up due to plantUML not rendering. 
![pLbjS-Es4VxENu4gCorPsMxxLKxTw2ovKMhPhgIxDfF9U70aB3D74Ye0dK-vNdzxiI2m0acGkfjaElMCJNBtsSMp2s3nuc-aeaBjooBwZNfc9IEfKEUqVCj49D_brOuAMfAqe59EbHBvsxrYYvHNw_ndHluOHKP3bZIloCU8w9zngTzZAhQIJCXQMrJR7tvCzFjxdcVH9sVnUZ7_mDAzod](https://github.com/23wc01/T-CS3330-FinalProject/assets/132469274/91f45ff5-9668-4cab-a96b-dcf7030a07da)

![class diagram](https://github.com/23wc01/T-CS3330-FinalProject/assets/132469274/f220b5e5-0de6-4445-8176-47eef68cc782)