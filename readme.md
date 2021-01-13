# BIE-PJV Project 

FIT CVUT 2019-2020 Semester Project  
  
## Mp3 Player using JavaFx   Rocker!!
  

 - An Mp3 player designed using JavaFx, with local persistence for keeping the record of last seven songs played.

- External Library used j1.0.jar - JLayer - MP3 Library.

- Basic functionalities - Start/Stop/Play implemented by the external library. Open implemented using FileChooser. Buttons mapped to each functionality. 

- Playlist saves on play and exit. If there isn't a file the program creates one,  otherwise it loads and keeps adding to it. 

- Java Collections used 
  ```ListView, ArrayList, ObservableList.```


```void setPlaylist()``` - Checks if playlist.txt exists, loads it. Otherwise, No playlist found is displayed.

```void storePlaylist()``` - Creates a new File if file doesn't exists, and writes the playlist to it. If file exists it appends to it.  

