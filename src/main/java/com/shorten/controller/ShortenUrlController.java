package com.shorten.controller;

import com.shorten.common.DefaultHttpResponse;
import com.shorten.common.code.BaseCode;
import com.shorten.model.LongUrlResponse;
import com.shorten.model.ShortenUrlReq;
import com.shorten.service.ShortenUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/shorten")
public class ShortenUrlController {

    private ShortenUrlService shortenUrlService;

    ShortenUrlController(ShortenUrlService shortenUrlService){
        this.shortenUrlService = shortenUrlService;
    }

    @PostMapping("")
    public DefaultHttpResponse<String> convertShortenUrl(@RequestBody @Validated ShortenUrlReq req ){

        log.debug("convertShortenUrl req is : {}", req);
        DefaultHttpResponse<String> response = new DefaultHttpResponse<>();

        if(req == null){
            return new DefaultHttpResponse<>(BaseCode.ERR_PARAM);
        }

        System.out.println("come here");
        String existUrl = shortenUrlService.existUrl(req.getUrl());

        if(existUrl != null){
            response.setResult(existUrl);
            response.setCode(BaseCode.SUCCESS);
            return response;
        }

        shortenUrlService.convertLongUrl(req.getUrl());

        return new DefaultHttpResponse<>(BaseCode.SUCCESS);
    }

    @GetMapping("/{shortUrl}")
    public DefaultHttpResponse<LongUrlResponse> getLongUrl(@PathVariable(value = "shortUrl")String shortUrl ){

        log.debug("come here {}",shortUrl);
        if(shortUrl == null) return new DefaultHttpResponse<>(BaseCode.ERR_PARAM);

        //db check by url
        return shortenUrlService.selectLongUrl(shortUrl);
    }
}
