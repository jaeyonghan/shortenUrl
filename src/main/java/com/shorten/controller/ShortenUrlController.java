package com.shorten.controller;

import com.shorten.common.DefaultHttpResponse;
import com.shorten.common.code.BaseCode;
import com.shorten.common.util.XssFilter;
import com.shorten.model.LongUrlReq;
import com.shorten.model.LongUrlResponse;
import com.shorten.model.ShortenUrlReq;
import com.shorten.service.ShortenUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequestMapping("/shorten")
public class ShortenUrlController {
    final static String URL_PREFIX = "mu.sa/";
    private ShortenUrlService shortenUrlService;

    ShortenUrlController(ShortenUrlService shortenUrlService){
        this.shortenUrlService = shortenUrlService;
    }

    @PostMapping("")
    public DefaultHttpResponse<String> convertShortenUrl(@RequestBody ShortenUrlReq req ) throws NoSuchAlgorithmException {
        log.debug("convertShortenUrl req is : {}", req);

        DefaultHttpResponse<String> response = new DefaultHttpResponse<>();

        if(req == null){
            return new DefaultHttpResponse<>(BaseCode.ERR_PARAM);
        }
        req.setLongUrl(XssFilter.XssReplace(req.getLongUrl()));

        if(!req.getLongUrl().startsWith("HTTP") && !req.getLongUrl().startsWith("HTTPS")
                && !req.getLongUrl().startsWith("http") && !req.getLongUrl().startsWith("https"))
            return new DefaultHttpResponse<>(BaseCode.ERR_PARAM);

        String url = shortenUrlService.convertShortenUrl(req.getLongUrl());
        response.setResult(URL_PREFIX+url);
        response.setCode(BaseCode.SUCCESS);

        return response;
    }

    @GetMapping("/")
    public DefaultHttpResponse<LongUrlResponse> getLongUrl(@RequestBody LongUrlReq req){
        log.debug("getLongUrl req is1213 : {}", req);


        if(req == null) return new DefaultHttpResponse<>(BaseCode.ERR_PARAM);
        if(!req.getShortUrl().contains(URL_PREFIX)) return new DefaultHttpResponse<>(BaseCode.ERR_PARAM);

        String shortUrlInfo = req.getShortUrl().replace(URL_PREFIX,"");

        return shortenUrlService.selectLongUrl(shortUrlInfo);
    }
}
