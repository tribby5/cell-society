package cellsociety_team13;

/**
 * This class represents what might go wrong when using XML files.
 * 
 * based on a class by Robert C. Duvall
 */
public class XMLException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public XMLException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    public XMLException (Throwable cause) {
        super(cause);
    }
}