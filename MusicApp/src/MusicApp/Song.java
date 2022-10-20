package MusicApp;

/**
 * The Song class, which stores information about a song
 */
public class Song {
    private Artist artist;
    private String name;
    private int numberOfPlays;

    /**
     * Creates a new song
     * @param name The name of the song
     */
    public Song(String name) {
        this.name = name;
        this.numberOfPlays = 0;
    }

    /**
     * Creates a new song
     * @param name The name of the song
     * @param numberOfPlays The number of times the song has been played
     */
    public Song(String name, int numberOfPlays) {
        this.name = name;
        this.numberOfPlays = numberOfPlays;
    }

    /**
     * Gets the artist of the song
     * @return The artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets the name of the song
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of times the song has been played
     * @return The number of times the song has been played
     */
    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    /**
     * 'Play' the song, incrementing the number of plays
     */
    public void play() {
        this.numberOfPlays += 1;
    }
}
