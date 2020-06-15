package com.shorten.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShortenUrlMapper {
    String selectShortenUrl(String longUrl);
    String selectLongUrl(String shortUrl);

}
