package com.shorten.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShortenUrlReq {

    @JsonProperty("long_url")
    private String longUrl;
}
