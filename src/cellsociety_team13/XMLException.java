package cellsociety_team13;

/**
 * A type of exception that is thrown when an error is caught in with xml reader or writer.
 * @author Andres Lebbos (afl13)
 */
public class XMLException extends Exception {
    private static final long serialVersionUID = 1L;

    public XMLException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    public XMLException (Throwable cause) {
        super(cause);
    }
}