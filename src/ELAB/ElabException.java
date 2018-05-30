package ELAB;

public class ElabException extends Exception {
    private String errorText;

    public ElabException() {
        super();
        this.errorText = "Unknown Error";
    }

    public ElabException(String errorText) {
        super();
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }
}
