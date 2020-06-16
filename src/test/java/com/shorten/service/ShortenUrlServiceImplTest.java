package com.shorten.service;

import com.shorten.model.ShortenUrlReq;
import com.shorten.repository.ShortenUrlMapper;
import com.shorten.testenv.ShortenUrlRequestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShortenUrlServiceImplTest {
    @InjectMocks ShortenUrlServiceImpl shortenUrlService;
    @Mock
    ShortenUrlMapper shortenUrlMapper;

    @Test
    public void convert_url_check_exist_url_info() throws NoSuchAlgorithmException {
        //given
        ShortenUrlReq req = ShortenUrlRequestFactory.getValidObject();

        //when
        when(shortenUrlMapper.isRegisteredLongUrl(req.getLongUrl())).thenReturn(true);
        when(shortenUrlMapper.updateSelectCount(req.getLongUrl())).thenReturn(1);
        when(shortenUrlMapper.isRegisteredShortUrl(req.getLongUrl())).thenReturn(false);
        when(shortenUrlMapper.registUrl(req.getLongUrl(),"test")).thenReturn(1);

        shortenUrlService.convertShortenUrl(req.getLongUrl());

        //then
        verify(shortenUrlMapper, times(1)).updateSelectCount(req.getLongUrl());
        verify(shortenUrlMapper, times(1)).selectShortenUrl(req.getLongUrl());
        verify(shortenUrlMapper, times(0)).isRegisteredShortUrl(req.getLongUrl());

    }

    @Test
    public void convert_url_not_exist_url_info() throws NoSuchAlgorithmException {
        //given
        ShortenUrlReq req = ShortenUrlRequestFactory.getValidObject();

        //when
        when(shortenUrlMapper.isRegisteredLongUrl(req.getLongUrl())).thenReturn(false); //not exist
        when(shortenUrlMapper.isRegisteredShortUrl(req.getLongUrl())).thenReturn(false);
        when(shortenUrlMapper.registUrl(req.getLongUrl(),"test")).thenReturn(1);

        shortenUrlService.convertShortenUrl(req.getLongUrl());

        //then
        verify(shortenUrlMapper, times(0)).updateSelectCount(req.getLongUrl());
        verify(shortenUrlMapper, times(0)).selectShortenUrl(req.getLongUrl());
        //check shortening key!
        verify(shortenUrlMapper, times(1)).registUrl(req.getLongUrl(), "hrJqhNUS");

    }


}