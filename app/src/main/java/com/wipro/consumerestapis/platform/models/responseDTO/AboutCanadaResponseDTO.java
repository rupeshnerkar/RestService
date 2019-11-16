package com.wipro.consumerestapis.platform.models.responseDTO;

import java.util.List;

public class AboutCanadaResponseDTO {

    private String title;
    private List<CountryRow> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CountryRow> getRows() {
        return rows;
    }

    public void setRows(List<CountryRow> rows) {
        this.rows = rows;
    }

}
