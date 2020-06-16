package com.shorten.testenv;

import com.shorten.model.ShortenUrlReq;

public class ShortenUrlRequestFactory {
    private static ShortenUrlReq validObject;
    static {
        validObject = new ShortenUrlReq();
        validObject.setLongUrl("HTTP://www.musinsa.com");
    }

    private static ShortenUrlReq inValidObject;
    static {
        inValidObject = new ShortenUrlReq();
        inValidObject.setLongUrl("www.musinsa.com");
    }


    public static ShortenUrlReq getValidObject(){return validObject;}
    public static ShortenUrlReq getInvalidObject(){return inValidObject;}
}
