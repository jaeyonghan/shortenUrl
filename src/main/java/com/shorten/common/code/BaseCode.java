package com.shorten.common.code;

public enum BaseCode {
    SUCCESS(0,"Success")
    ,ERR_EXCEPTION(500, "Exception Occured.")
    ,ERR_PARAM(502, "Parameter Error.")
    ,ERR_CONVERT_URL(601, "Error Convert Url.")
    ;

    private final int code;
    private final String message;

    BaseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int code(){return this.code;}
    public String message(){return  this.message;}
}
