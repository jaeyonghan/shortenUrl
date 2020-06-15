package com.shorten.service;

import com.shorten.common.DefaultHttpResponse;
import com.shorten.common.code.BaseCode;
import com.shorten.model.LongUrlResponse;
import com.shorten.repository.ShortenUrlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

    private ShortenUrlMapper shortenUrlMapper;

    ShortenUrlServiceImpl(ShortenUrlMapper shortenUrlMapper){
        this.shortenUrlMapper = shortenUrlMapper;
    }

    @Override
    public DefaultHttpResponse<String> convertLongUrl(String url) {
        return null;
    }

    @Override
    public String existUrl(String longUrl) {

        System.out.println("came impl");
        String result = shortenUrlMapper.selectShortenUrl(longUrl);

        System.out.println("result is :"+result);
        return result;
    }

    @Override
    public DefaultHttpResponse<LongUrlResponse> selectLongUrl(String shortUrl) {
        DefaultHttpResponse<LongUrlResponse> response = new DefaultHttpResponse<>(BaseCode.SUCCESS);

        LongUrlResponse longUrlResponse = new LongUrlResponse();

        String longUrl = shortenUrlMapper.selectLongUrl(shortUrl);
        log.debug("long url is : {}", longUrl);
        longUrlResponse.setLongUrl(longUrl);
        response.setResult(longUrlResponse);
        return response;
    }
}
