package models.response.response400;

public class Row {
    private String field;
    private String reason;
    private String message;

    public Row() {
    }

    public Row(String field, String reason, String message) {
        this.field = field;
        this.reason = reason;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
