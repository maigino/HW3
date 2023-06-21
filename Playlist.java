import java.util.*;

/**
 * A class representing a playlist.
 * Implements the {@link OrderSongIterable}, {@link FilteredSongIterable}, and {@link Cloneable} interfaces.
 */
public class Playlist implements OrderSongIterable, FilteredSongIterable, Cloneable {
    private List<Song> songs;
    private ScanningOrder scanningOrder;
    private String filterArtist;
    private Enum filterGenre;
    private int filterDuration;
    private int size;
    private List<Song> filteredSongs;
    private int filteredSongsSize;
    private List<Song> originalOrder;

    /**
     * Constructs a new Playlist object.
     * Initializes the playlist with an empty list of songs, sets the default scanning order to ADDING,
     * and initializes other variables.
     */
    public Playlist() {
        this.songs = new ArrayList<>();
        this.scanningOrder = ScanningOrder.ADDING;
        this.originalOrder = new ArrayList<>();
        this.filteredSongs = new ArrayList<>();
        this.filteredSongsSize = 0;
        this.filterArtist = null;
        this.filterGenre = null;
        this.filterDuration = -1;
        this.size = 0;
    }

    /**
     * Adds a song to the playlist.
     *
     * @param song the song to add
     * @throws SongAlreadyExistsException if the song already exists in the playlist
     */
    public void addSong(Song song) throws SongAlreadyExistsException {
        for (Song existingSong : songs) {
            if (existingSong.equals(song)) {
                throw new SongAlreadyExistsException();
            }
        }
        size++;
        originalOrder.add(song);
        songs.add(song);
    }

    /**
     * Removes a song from the playlist.
     *
     * @param song the song to remove
     * @return true if the song was removed successfully, false otherwise
     */
    public boolean removeSong(Song song) {
        int index = 0;
        for (Song existingSong : songs) {
            if (existingSong.equals(song)) {
                songs.remove(index);
                size--;
                originalOrder.remove(index);
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Sets the scanning order for the playlist.
     *
     * @param order the scanning order
     */
    public void setScanningOrder(ScanningOrder order) {
        this.scanningOrder = order;
        if (scanningOrder == ScanningOrder.ADDING) {
            songs = new ArrayList<>(originalOrder);
        } else {
            switch (scanningOrder) {
                case NAME:
                    songs.sort(Comparator.comparing(Song::getName).thenComparing(Song::getArtist));
                    break;
                case DURATION:
                    songs.sort(Comparator.comparing(Song::getDuration));
                    break;
                default:
                    // No sorting needed for ADDING order
                    break;
            }
        }

    }

    /**
     * Checks if the given song is in the filtered songs.
     *
     * @param x the song to check
     * @return {@code true} if the song is not in, {@code false} otherwise
     */
    public boolean checkSong(Song x) {
        for (int i = 0; i < filteredSongsSize; i++) {
            if (x == filteredSongs.get(i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Filters the songs in the playlist based on the artist.
     *
     * @param artist the artist to filter by
     */
    @Override
    public void filterArtist(String artist) {
        this.filterArtist = artist;
        for (int i = 0; i < size; i++) {
            Song current = songs.get(i);
            if (artist != null) {
                if (!artist.equals(current.getArtist())) {
                    if (checkSong(current)) {
                        filteredSongs.add(current);
                        filteredSongsSize++;
                    }
                }
            }
        }
    }

    /**
     * Filters the songs in the playlist based on the genre.
     *
     * @param genre the genre to filter by
     */
    @Override
    public void filterGenre(Enum genre) {
        this.filterGenre = genre;
        for (int i = 0; i < size; i++) {
            Song current = songs.get(i);
            if (!genre.equals(current.getGenre())) {
                if (genre != null) {
                    if (checkSong(current)) {
                        filteredSongs.add(current);
                        filteredSongsSize++;
                    }
                }
            }
        }
    }

    /**
     * Filters the songs in the playlist based on the duration.
     *
     * @param duration the duration to filter by
     */
    @Override
    public void filterDuration(int duration) {
        this.filterDuration = duration;
        for (int i = 0; i < size; i++) {
            Song current = songs.get(i);
            if (duration < current.getDuration()) {
                if (checkSong(current)) {
                    filteredSongs.add(current);
                    filteredSongsSize++;
                }
            }
        }
    }
    /**
     * Creates and returns a copy of the playlist.
     *
     * @return a new instance of the Playlist class with the same songs as the original
     */
    @Override
    public Playlist clone() {
        Playlist temp = new Playlist();
        temp.songs = new ArrayList<>(this.songs.size());
        for (Song song : this.songs) {
            if (song != null) {
                temp.songs.add(song.clone());
            } else {
                temp.songs.add(null);
            }
        }
        return temp;
    }
    /**
     * Checks whether the playlist is equal to the given object.
     *
     * @param other the object to compare with the playlist
     * @return true if the playlist is equal to the given object, false otherwise
     */

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Playlist)) {
            return false;
        }
        Playlist otherPlaylist = (Playlist) other;
        if (songs.size() != otherPlaylist.songs.size()) {
            return false;
        }
        for (Song song : songs) {
            if (!otherPlaylist.songs.contains(song)) {
                return false;
            }
        }
        if (filterArtist == null) {
            if (otherPlaylist.filterArtist != null) {
                return false;
            }
        } else if (!filterArtist.equals(otherPlaylist.filterArtist)) {
            return false;
        }
        if (filterGenre == null) {
            if (otherPlaylist.filterGenre != null) {
                return false;
            }
        } else if (!filterGenre.equals(otherPlaylist.filterGenre)) {
            return false;
        }
        return filterDuration == otherPlaylist.filterDuration;
    }
    /**
     * Returns a string representation of the playlist.
     *
     * @return a string representation of the playlist
     */

    @Override
    public String toString() {
        String result = "[";
        int size = songs.size();
        for (int i = 0; i < size; i++) {
            Song song = songs.get(i);
            result += "(" + song.toString() + ")";
            if (i != size - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
    /**
     * Returns an iterator over the songs in the playlist.
     *
     * @return an iterator over the songs in the playlist
     */
    @Override
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * Iterator implementation for iterating over the songs in the playlist.
     */
    private class PlaylistIterator implements Iterator<Song> {
        private int currentIndex = 0;

        /**
         * Checks if there are more songs to iterate.
         *
         * @return true if there are more songs, false otherwise.
         */
        @Override
        public boolean hasNext() {
            for (int i = 0; i < filteredSongsSize; i++) {
                if (currentIndex < size && songs.get(currentIndex) == filteredSongs.get(i)) {
                    currentIndex++;
                    filteredSongs.remove(songs.get(currentIndex-1));
                    filteredSongsSize--;
                    return hasNext();
                }
            }
            return currentIndex < size;
        }
        /**
         * Returns the next song in the iteration.
         *
         * @return the next song
         */
        @Override
        public Song next() {
            currentIndex++;
            return songs.get(currentIndex - 1);
        }
    }
    /**
     * Returns the hash code value for the playlist.
     *
     * @return the hash code value for the playlist
     */

    @Override
    public int hashCode() {
        int result = 0;
        result += Objects.hashCode(filterArtist);
        result += calculateSongHashCode(songs);
        return result;
    }
    /**
     * Calculates the hash code value for a list of songs.
     *
     * @param songs the list of songs
     * @return the calculated hash code value
     */

    private int calculateSongHashCode(List<Song> songs) {
        int sum = 0;
        for (Song song : songs) {
            sum += song.hashCode();
        }
        return sum;
    }
}