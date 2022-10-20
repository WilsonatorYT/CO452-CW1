# Programming Concepts - Assignment 1
### Student Name: Ben Wilson <br/>Student ID: 21607984

### Introduction
This is my wiki file for the first assignment in the module. I've tried to log my development process as much as 
possible. I've also tried to include as much detail as possible in the code comments, which should explain the
functionality of each class, function, etc.

### Development Process
This section will act as a log for my development process for the application - I will explain the major features and my implementation process.

#### 1.	Creating the Command System
For my implementation of the Music App, I decided that since the program is a console application, 
I’d like to create a more organized command system, rather than using a mess of if/else and switch 
statements.

For this purpose, I created the `ConsoleSystem` class. This contains a `Map` of `Command` objects, 
which can be registered by calling the `registerCommand` function. This allows for easy command 
registration – each `Command` has a name (the actual command definition), a description 
(used by the ‘help’ command to display information about what it does), and a `Consumer` action.

This action takes an array of `String`, for parameters to be passed to the command.
I decided to use this method as it allows for very easy command registration and is extremely quick to 
expand. It also helps with readability, as each command is registered separately and not within a giant 
iterator.

For the input, I have used the InputReader class that was provided for the early week exercises, 
for convenience.

#### 2.	Initial Commands
To begin, I wanted to create some basic commands – mainly to test that my new command system was working
as intended.

I first added the `help` command, which retrieves all the commands in the `ConsoleSystem`, 
and prints them to the console, along with the description.

I also added an `exit` command, which simply runs the `exit()` function on the `ConsoleSystem`, setting 
the `wantsToExit` field to true, which in turn exits the while loop and subsequently the program.

#### 3.	Song/Artist Structure
My next step was to begin writing the basic code for the `Song` and `Artist` classes. I also created a 
class named `App` to store all the data, and functions to handle it.

The assignment brief asked for the application to store a list of songs, with an artist name – I have 
decided to do things a little differently and tie the actual `Song` list to an `Artist` object, with the 
`App` class providing a `getAllSongs()` function to retrieve everything if necessary. I think this works 
better for the purposes of my application.

For now, I have decided to store the actual data as static objects within the `Artist` class, as I believe
saving and loading data is outside the scope of this assignment.

#### 4.	Adding Songs
Next, I created a new registered command called `add_song`, which takes two arguments: the name of
the artist, and the name of the new song.

The program looks for an artist with the given name, and if there isn’t one, it creates a new artist. 
Then, it adds the new song to the artist’s song list.

#### 5.	Song Finding
As I was thinking about the next step (removing a song), I decided it would be a good idea to 
add a general song search function, to get a song by its name. This will be used in the 
`remove_song` command, and I may also use it for a general song search command as an extra feature.

For this, the `Artist` class has a `findSong` function, which first checks for a song with the exact 
name as the one given, and if nothing is found, it performs a 
[Levenshtein Distance](https://en.wikipedia.org/wiki/Levenshtein_distance) search to find a 
closely matching name (allows for small spelling mistakes).

I have now also added the `find_song` command, which takes a song name as an argument and 
searches for the song using the above functionality.

#### 6.	Removing Songs
Using the above song-finding functionality, it was very simple to create the `remove_song` command, 
which can either take just the song name, or the artist and song name (in case there are two 
songs with the same name, by two different artists).

#### 7. Playing Songs
I’ve added a very simple command `play_song`, which just increments the number of plays by one.

This was outside the scope of the assignment, but I thought it would be a nice feature to allow the
play count to change during the program’s runtime.

#### 8.	Displaying Songs
I’ve added a command `find_songs`, which can take an optional argument of an artist name.

If there is no artist name given, it finds all the songs in the application and prints the name, 
artist and number of plays to the console. If there IS an artist, it does the same but only for the
given artist’s songs.

I’ve also added the `find_popular_songs` command, which does the same as above but filters the 
song list based on the given number of plays argument (only those that are equal or above the number 
of plays).
