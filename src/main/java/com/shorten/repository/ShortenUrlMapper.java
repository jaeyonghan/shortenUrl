package com.shorten.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShortenUrlMapper {
    String selectShortenUrl(String longUrl);
    String selectLongUrl(String shortUrl);
    boolean isRegisteredLongUrl(String longURL);
    boolean isRegisteredShortUrl(String shortUrl);
    Integer registUrl(@Param("longUrl") String longUrl, @Param("shortUrl") String shortUrl);
    Integer updateSelectCount(String longUrl);
}
