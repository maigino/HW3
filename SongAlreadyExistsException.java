/**
 * Exception thrown when attempting to add a song that already exists in the playlist.
 */
public class SongAlreadyExistsException extends RuntimeException {
    /**
     * Constructs a new SongAlreadyExistsException with a default error message.
     */
    public SongAlreadyExistsException() {
        super("Song already exists in the playlist.");
    }
}
