package com.shorten.service;

import com.shorten.common.DefaultHttpResponse;
import com.shorten.common.code.BaseCode;
import com.shorten.common.util.Base62;
import com.shorten.common.util.SHA256;
import com.shorten.model.LongUrlResponse;
import com.shorten.repository.ShortenUrlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

    final static long MAX = 218340105584895l;
    final static int SUB_STRING_LENGTH = 12;

    private ShortenUrlMapper shortenUrlMapper;

    ShortenUrlServiceImpl(ShortenUrlMapper shortenUrlMapper){
        this.shortenUrlMapper = shortenUrlMapper;
    }

    @Override
    public DefaultHttpResponse<String> convertLongUrl(String url) {
        return null;
    }

    @Override
    public String convertShortenUrl(String longUrl) throws NoSuchAlgorithmException {

        SHA256 sha256 = new SHA256();
        Base62 base62 = new Base62();
        String shorteningKey = "";

        if (shortenUrlMapper.isRegisteredLongUrl(longUrl)) {
            shortenUrlMapper.updateSelectCount(longUrl);
            return shortenUrlMapper.selectShortenUrl(longUrl);
        }

        String sha256Hash = sha256.encode(longUrl.getBytes());

        for (int i=0; i<=sha256Hash.length()-SUB_STRING_LENGTH; i++) {
            String digits = sha256Hash.substring(i, i+SUB_STRING_LENGTH);
            long longKey = Long.parseLong(digits, 16);

            if (longKey >= MAX) {
                continue;
            }

            shorteningKey = base62.encode(longKey);
            if (shortenUrlMapper.isRegisteredShortUrl(shorteningKey)) {
                log.info("exist key : {}", shorteningKey);
            } else {
                shortenUrlMapper.registUrl(longUrl, shorteningKey);
                break;
            }
        }
        return shorteningKey;
    }

    @Override
    public DefaultHttpResponse<LongUrlResponse> selectLongUrl(String shortUrl) {

        DefaultHttpResponse<LongUrlResponse> response = new DefaultHttpResponse<>(BaseCode.SUCCESS);

        LongUrlResponse longUrlResponse = new LongUrlResponse();

        String longUrl = shortenUrlMapper.selectLongUrl(shortUrl);
        longUrlResponse.setLongUrl(longUrl);
        response.setResult(longUrlResponse);

        return response;
    }


}
