package com.shorten.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LongUrlReq {

    @JsonProperty("short_url")
    private String shortUrl;
}
