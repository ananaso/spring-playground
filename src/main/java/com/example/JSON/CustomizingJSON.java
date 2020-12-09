package com.example.JSON;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomizingJSON {
    @JsonIgnore
    private String mySecretCodez;

    private int weight;
    private String description;
    private Date departsOn;

    @JsonProperty("the-weight")
    public int getWeight() {
        return weight;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("DESCRIPTION")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDepartsOn() {
        return departsOn;
    }

}
