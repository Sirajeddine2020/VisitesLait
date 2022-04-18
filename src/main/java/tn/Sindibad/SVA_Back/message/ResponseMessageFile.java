package tn.Sindibad.SVA_Back.message;

public class ResponseMessageFile {
    private String message;
    public ResponseMessageFile(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
