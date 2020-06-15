package com.shorten.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LongUrlResponse {

    @JsonProperty("long_url")
    private String longUrl;
}
