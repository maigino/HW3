/**
 * An interface for iterating over a collection of songs with filtering capabilities.
 */
public interface FilteredSongIterable extends Iterable<Song> {

    /**
     * Filters the songs based on the specified artist.
     *
     * @param artist the artist to filter by
     */
    void filterArtist(String artist);

    /**
     * Filters the songs based on the specified genre.
     *
     * @param genre the genre to filter by
     */
    void filterGenre(Enum genre);

    /**
     * Filters the songs based on the specified duration.
     *
     * @param duration the maximum duration to filter by
     */
    void filterDuration(int duration);
}
