package com.shorten.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shorten.common.code.BaseCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DefaultHttpResponse<T> {
    @JsonProperty("return_code")
    Integer returnCode;

    @JsonProperty("return_message")
    String returnMessage;

    @JsonProperty("context")
    T result;

    public DefaultHttpResponse(BaseCode baseCode) {
        this.setCode(baseCode);
    }

    public void setCode(BaseCode baseCode) {
        setReturnCode(baseCode.code());
        setReturnMessage(baseCode.message());
    }
}