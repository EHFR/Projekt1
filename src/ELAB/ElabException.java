package ELAB;

class ElabException extends Exception {
    private String errorText;

    ElabException(String errorText) {
        super();
        this.errorText = errorText;
    }

    String getErrorText() {
        return errorText;
    }
}
