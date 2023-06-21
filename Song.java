/**
 * A class representing a song.
 * Implements the {@link Cloneable} interface.
 */
public class Song implements Cloneable {
    private final String name;
    private final String artist;
    private Genre genre;
    private int duration;

    /**
     * Constructs a new Song object with the specified name, artist, genre, and duration.
     *
     * @param name     the name of the song
     * @param artist   the artist of the song
     * @param genre    the genre of the song
     * @param duration the duration of the song in seconds
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    /**
     * Returns the name of the song.
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the genre of the song.
     *
     * @return the genre of the song
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Returns the duration of the song in seconds.
     *
     * @return the duration of the song in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the genre of the song.
     *
     * @param genre the genre of the song
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Sets the duration of the song in seconds.
     *
     * @param duration the duration of the song in seconds
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Checks if this song is equal to the specified object.
     *
     * @param other the object to compare
     * @return true if the song is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Song)) {
            return false;
        }
        Song otherSong = (Song) other;
        return this.name.equals(otherSong.name) && this.artist.equals(otherSong.artist);
    }

    /**
     * Creates and returns a clone of this song.
     *
     * @return a clone of this song
     */
    @Override
    public Song clone() {
        try {
            Song clonedSong = (Song) super.clone();
            return clonedSong;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Returns a string representation of the song.
     *
     * @return a string representation of the song
     */
    @Override
    public String toString() {
        return name + ", " + artist + ", " + genre + ", " + printDuration(duration);
    }

    /**
     * Returns the hash code value for the song.
     *
     * @return the hash code value for the song
     */
    @Override
    public int hashCode() {
        int result = 0;
        result = result + calculateAsciiSum(name);
        result = result + calculateAsciiSum(artist);
        return result;
    }

    /**
     * Calculates the sum of ASCII values for the characters in a string.
     *
     * @param str the string
     * @return the sum of ASCII values
     */
    private int calculateAsciiSum(String str) {
        int sum = 0;
        for (char c : str.toCharArray()) {
            sum += (int) c;
        }
        return sum;
    }

    /**
     * Enum representing the possible genres of a song.
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }

    /**
     * Formats the duration of the song into a string representation.
     *
     * @param secondDuration the duration of the song in seconds
     * @return the formatted duration string
     */
    public String printDuration(int secondDuration) {
        String songDuration;
        if (secondDuration > 59) {
            int minutes = secondDuration / 60;
            int seconds = secondDuration % 60;
            String StringMinutes;
            String StringSeconds;
            StringMinutes = "" + minutes + "";
            if (seconds < 10) {
                StringSeconds = "0" + seconds;
            } else {
                StringSeconds = "" + seconds + "";
            }
            return StringMinutes + ":" + StringSeconds;
        } else {
            songDuration = "00:" + secondDuration;
        }
        return songDuration;
    }
}
