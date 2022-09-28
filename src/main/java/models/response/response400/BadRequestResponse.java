package models.response.response400;

import java.util.List;

public class BadRequestResponse {
    private String type;
    private List<Row> rows;

    public BadRequestResponse() {
    }

    public BadRequestResponse(String type, List<Row> rows) {
        this.type = type;
        this.rows = rows;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
