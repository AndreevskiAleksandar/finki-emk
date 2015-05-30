package mk.ukim.finki.wp.web.errors;

/**
 * Created by Aleksandar on 4/5/2015.
 */
public class AttachmentBlobCreationException extends Exception {
    public AttachmentBlobCreationException(String message) {
        super(message);
    }

    public AttachmentBlobCreationException(Throwable cause) {
        super(cause);
    }
}
