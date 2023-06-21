/**
 * An interface for iterating over a collection of songs with scanning order capabilities.
 */
public interface OrderSongIterable extends Iterable<Song> {

    /**
     * Sets the scanning order for iterating over the songs.
     *
     * @param order the scanning order to set
     */
    void setScanningOrder(ScanningOrder order);
}
