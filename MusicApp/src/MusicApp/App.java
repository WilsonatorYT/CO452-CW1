package MusicApp;

import MusicApp.ConsoleSystem.ConsoleSystem;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The application class, which stores all the Songs, Artists, etc. And functions to manipulate them
 */
public class App {
    private ArrayList<Artist> artists = new ArrayList<>();

    /**
     * Constructor, which adds all the artists to the application
     */
    public App() {
        // Later, this could be replaced with loading from a JSON file
        artists.add(Artist.eminem);
        artists.add(Artist.michaelJackson);
        artists.add(Artist.queen);
        artists.add(Artist.tupac);
        artists.add(Artist.brunoMars);

        // Register the add song command
        ConsoleSystem.registerCommand("add_song", "Adds a new song to the app. Usage: add_song <artist name> \"<song name>\"", (args) -> {
            // Check if the user has specified the correct number of arguments
            if (args.length != 3) {
                System.out.println("Invalid number of arguments! Usage: add_song <artist name> <song name>");
                return;
            }

            // Get the arguments
            String artistName = args[1];
            String songName = args[2];

            // Check if the artist exists, and if not, create a new artist
            Artist artist = getArtist(artistName);
            if (artist == null) {
                artist = new Artist(artistName);
                artists.add(artist);
            }

            // Create the song
            Song song = new Song(songName, 0);
            artist.addSong(song);

            System.out.println("Added song " + songName + " by " + artist.getName());
        });

        // Register the remove song command
        ConsoleSystem.registerCommand("remove_song", "Removes a song from the app. Usage: remove_song <artist name - optional> <song name>", (args) -> {
            // Check if the user has specified the correct number of arguments (artist name is optional)
            if (args.length < 2 || args.length > 3) {
                System.out.println("Invalid number of arguments! Usage: remove_song <artist name - optional> <song name>");
                return;
            }

            // Create variables to hold song and artist data
            String artistName = null;
            String songName;
            Song song = null;
            Artist artist = null;

            if (args.length == 2) {
                songName = args[1].replace("\"", ""); // Remove quotes
                // Perform an app-wide search for the song
                song = findSongByName(songName);
            }
            else {
                artistName = args[1];
                songName = args[2].replace("\"", ""); // Remove quotes
                // Check if the artist exists
                artist = getArtist(artistName);
                if (artist == null) {
                    System.out.println("No artist found with the name " + artistName);
                    return;
                }

                // Update artistName just in case the artist name defined is not of the correct case
                artistName = artist.getName();

                // Perform a local search for the song in the artist
                song = artist.findSong(songName);
            }

            // Handle song being null
            if (song == null) {
                if (artistName == null) {
                    System.out.println("No song found with the name " + songName);
                }
                else {
                    System.out.println("No song found with the name " + songName + " by " + artistName);
                }
                return;
            }

            // Set songName to the actual name of the song (fixing case mismatch)
            songName = song.getName();

            // If artist is still null, get it from the song
            if (artist == null) {
                artist = song.getArtist();
            }

            // Remove the song from its artist
            artist.removeSong(song);
            System.out.println("Removed song " + songName + " by " + artist.getName());
        });

        // Register the find song command
        ConsoleSystem.registerCommand("find_song", "Finds a song in the app. Usage: find_song <song name>", (args) -> {
            // Check if the user has specified the correct number of arguments
            if (args.length != 2) {
                System.out.println("Invalid number of arguments! Usage: find_song <song name>");
                return;
            }

            // Get the song name from the arguments
            String songName = args[1];

            // Call the method to find the song
            Song song = findSongByName(songName);

            // If the song is not found, print an error
            if (song == null) {
                System.out.println("No song found with the name \"" + songName + "\"");
                return;
            }

            // Print the song, the artist and the number of plays
            System.out.println("Found song:");
            System.out.println("Song Title: " + song.getName());
            System.out.println("Artist: " + song.getArtist().getName());
            System.out.println("Plays: " + song.getNumberOfPlays());
        });

        // Register the play song command
        ConsoleSystem.registerCommand("play_song", "Starts playing a song. Usage: play_song \"<song name\"", (args) -> {
            // Check if the user has specified the correct number of arguments
            if (args.length != 2) {
                System.out.println("Invalid number of arguments! Usage: play_song <song name>");
                return;
            }

            // Get the song name from the arguments
            String songName = args[1];

            // Get the song object
            Song song = findSongByName(songName);

            if (song == null) {
                System.out.println("No song found with the name \"" + songName + "\"");
                return;
            }

            // Start playing
            song.play();
            System.out.println("Playing '" + song.getName() + "' by " + song.getArtist().getName());
        });

        // Register the find_songs command
        ConsoleSystem.registerCommand("find_songs", "Displays a list of all songs, with an optional artist name. Usage: find_songs <artist name - optional>", (args) -> {
            // Check if the user has specified the correct number of arguments
            if (args.length > 2) {
                System.out.println("Invalid number of arguments! Usage: find_songs <artist name - optional>");
                return;
            }

            // If there is no artist argument, just print all the songs
            if (args.length == 1) {
                // Loop through all the artists and their songs
                artists.forEach(artist -> artist.getSongs().forEach(song -> {
                    System.out.println(song.getName() + " by " + artist.getName() + " (" + song.getNumberOfPlays() + " plays)");
                }));
            }
            else {
                // Get the artist argument
                String artistName = args[1].replace("\"", "");

                // Get the artist object
                Artist artist = getArtist(artistName);

                // Check if the artist exists
                if (artist == null) {
                    System.out.println("No artist found with the name " + artistName);
                    return;
                }

                // Print the list of songs for the artist
                artist.getSongs().forEach(song -> {
                    System.out.println(song.getName() + " by " + artist.getName() + " (" + song.getNumberOfPlays() + " plays)");
                });
            }
        });

        // Register the find_popular_songs command
        ConsoleSystem.registerCommand("find_popular_songs", "Displays a list of all songs that have been played more than the given number of times. Usage: find_popular_songs <play count>", (args) -> {
            // Check if the user has specified the correct number of arguments
            if (args.length != 2) {
                System.out.println("Invalid number of arguments! Usage: find_popular_songs <play count>");
                return;
            }

            // Get the play count from the arguments, using error checking to ensure that it's a valid integer
            int playCount;
            try {
                playCount = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException ex) {
                System.out.println("Play count must be a positive integer!");
                return;
            }

            // Find a list of all songs that have been played at least playCount number of times
            var popularSongs = getAllSongs().stream().filter((song) -> {
                return song.getNumberOfPlays() >= playCount;
            }).collect(Collectors.toCollection(ArrayList::new));

            // Loop through all the songs and print their name, artist name and number of plays
            popularSongs.forEach(song->{
                System.out.println(song.getName() + " by " + song.getArtist().getName() + " (" + song.getNumberOfPlays() + " plays)");
            });
        });
    }

    /**
     * Perform an app-wide search for a song with the given name
     * @return The song, if it was found
     */
    private Song findSongByName(String songName) {
        // Loop through the artists and try to find the song from the artist
        for (Artist artist: artists) {
            Song song = artist.findSong(songName);
            // Check if a song was found and return it
            if (song != null) {
                return song;
            }
        }

        return null;
    }

    /**
     * Gets an artist by their name
     * @param artistName The name of the artist
     * @return The artist, or null if the artist doesn't exist
     */
    private Artist getArtist(String artistName) {
        // Look for the artist with the specified name
        for (Artist artist : artists) {
            if (artist.getName().equalsIgnoreCase(artistName)) {
                return artist;
            }
        }

        return null;
    }

    /**
     * Adds a new artist to the application
     * @param artist The artist to add
     */
    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    /**
     * Gets all artists in the application
     * @return The list of artists
     */
    public ArrayList<Artist> getArtists() {
        return artists;
    }

    /**
     * Gets all songs, by all artists
     * @return The list of songs
     */
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();

        for (Artist artist : artists) {
            songs.addAll(artist.getSongs());
        }

        return songs;
    }
}
