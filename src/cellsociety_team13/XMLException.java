package cellsociety_team13;

public class XMLException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public XMLException (String message, Object ... values) {
        super(String.format(message, values));
    }
    
    public XMLException (Throwable cause) {
        super(cause);
    }
}