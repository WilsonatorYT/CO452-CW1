package MusicApp;

import MusicApp.helpers.Utility;

import java.util.ArrayList;

/**
 * The Artist class, which stores information about an artist, and their songs
 */
public class Artist {

    /**
     * The name of the artist
     */
    public String name;

    /**
     * The list of songs by the artist
     */
    public ArrayList<Song> songs = new ArrayList<>();

    /**
     * Creates a new artist
     * @param name The name of the artist
     */
    public Artist(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the artist
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a new song to the artist
     * @param song The song to add
     */
    public Artist addSong(Song song) {
        songs.add(song);
        song.setArtist(this);
        return this;
    }

    /**
     * Gets all songs by the artist
     * @return The list of songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Attempts to find a song with the given name
     * @return The song, if found
     */
    public Song findSong(String songName) {
        Song song = null;

        // First, check if any songs match the song's name exactly
        for(Song s : songs) {
            if(s.getName().equalsIgnoreCase(songName)) {
                song = s;
                break; // Exit loop, as song has been found
            }
        }

        // If not, use Levenshtein distance to find the song (finds a close match)
        if(song == null) {
            for(Song s : songs) {
                if(Utility.getLevenshteinDistance(s.getName(), songName) <= 2) {
                    song = s;
                    break; // Break from the loop if a match is found
                }
            }
        }

        return song;
    }

    /**
     * Removes a specified song from the list
     * @param song The song to remove
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }

    // Below this point are static final objects that are used to store the artists
    public static final Artist eminem = new Artist("Eminem") {{
        addSong(new Song("Lose Yourself", 2332));
        addSong(new Song("Not Afraid", 4932));
        addSong(new Song("Rap God", 1237));
        addSong(new Song("The Monster", 2952));
        addSong(new Song("Venom", 901));
    }};

    public static final Artist michaelJackson = new Artist("Michael Jackson") {{
        addSong(new Song("Beat It", 905));
        addSong(new Song("Billie Jean", 723));
        addSong(new Song("Black or White", 3332));
        addSong(new Song("Smooth Criminal", 8022));
        addSong(new Song("Thriller", 5412));
    }};

    public static final Artist queen = new Artist("Queen") {{
        addSong(new Song("Bohemian Rhapsody", 7835));
        addSong(new Song("Another One Bites The Dust", 1234));
        addSong(new Song("Crazy Little Thing Called Love", 3134));
        addSong(new Song("I Want To Break Free", 1234));
        addSong(new Song("Under Pressure", 1537));
    }};

    public static final Artist tupac = new Artist("Tupac") {{
        addSong(new Song("Changes", 1235));
        addSong(new Song("I Ain't Mad At Cha", 718));
        addSong(new Song("Keep Your Head Up", 718));
        addSong(new Song("Life Goes On", 1237));
        addSong(new Song("California Love", 1238));
    }};

    public static final Artist brunoMars = new Artist("Bruno Mars") {{
        addSong(new Song("That's What I Like", 8234));
        addSong(new Song("24K Magic", 2134));
        addSong(new Song("Uptown Funk (feat. Mark Ronson)", 1237));
        addSong(new Song("Locked Out of Heaven", 5123));
        addSong(new Song("Grenade", 1234));
    }};
}
