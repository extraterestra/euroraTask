package models.response.response401;

public class UnauthorisedResponse {
    private int status;
    private String message;

    public UnauthorisedResponse() {
    }

    public UnauthorisedResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
