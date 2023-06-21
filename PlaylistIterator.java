import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An iterator for iterating over a list of songs in a playlist.
 */
class PlaylistIterator implements Iterator<Song> {
    private List<Song> songs;
    private int currentIndex = 0;

    /**
     * Constructs a PlaylistIterator with the specified list of songs.
     *
     * @param songs the list of songs to iterate over
     */
    public PlaylistIterator(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * Checks if there are more songs to iterate over.
     *
     * @return true if there are more songs, false otherwise
     */
    public boolean hasNext() {
        return currentIndex < songs.size();
    }

    /**
     * Retrieves the next song in the iteration.
     *
     * @return the next song
     * @throws NoSuchElementException if there are no more songs to iterate over
     */
    public Song next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return songs.get(currentIndex++);
    }
}
