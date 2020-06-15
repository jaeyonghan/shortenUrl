package com.shorten.service;

import com.shorten.common.DefaultHttpResponse;
import com.shorten.model.LongUrlResponse;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface ShortenUrlService {
    DefaultHttpResponse<String> convertLongUrl(String url);
    String existUrl(String longUrl);
    DefaultHttpResponse<LongUrlResponse> selectLongUrl(String shortUrl);
    String shorten(String longURL) throws NoSuchAlgorithmException;

}
