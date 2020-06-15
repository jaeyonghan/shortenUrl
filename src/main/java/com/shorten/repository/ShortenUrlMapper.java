package com.shorten.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShortenUrlMapper {
    String selectShortenUrl(String longUrl);
    String selectLongUrl(String shortUrl);
    boolean isRegisteredLongUrl(String longURL);
    boolean isRegisteredShortUrl(String ShortUrl);
    void registUrl(String longUrl, String shortUrl);

}
