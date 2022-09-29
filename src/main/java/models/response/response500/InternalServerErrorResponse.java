package models.response.response500;

public class InternalServerErrorResponse {
    private String id;

    public InternalServerErrorResponse() {
    }

    public InternalServerErrorResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
