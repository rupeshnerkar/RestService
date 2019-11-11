package com.example.consumerestapis.platform.models.responseDTO;



import java.util.List;

public class AboutCanadaResponseDTO {

    private String title;
    private List<CanadaRows> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CanadaRows> getRows() {
        return rows;
    }

    public void setRows(List<CanadaRows> rows) {
        this.rows = rows;
    }

}
