package com.shorten.service;

import com.shorten.common.DefaultHttpResponse;
import com.shorten.model.LongUrlResponse;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface ShortenUrlService {
    DefaultHttpResponse<String> convertLongUrl(String url);
    DefaultHttpResponse<LongUrlResponse> selectLongUrl(String shortUrl);
    String convertShortenUrl(String longURL) throws NoSuchAlgorithmException;

}
