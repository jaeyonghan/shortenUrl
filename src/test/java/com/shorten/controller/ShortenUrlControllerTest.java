package com.shorten.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.shorten.common.DefaultHttpResponse;
import com.shorten.common.code.BaseCode;
import com.shorten.model.LongUrlReq;
import com.shorten.model.LongUrlResponse;
import com.shorten.model.ShortenUrlReq;
import com.shorten.service.ShortenUrlService;
import com.shorten.testenv.AbstractMockController;
import com.shorten.testenv.ShortenUrlControllerInterface;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.security.NoSuchAlgorithmException;

import static com.shorten.common.code.BaseCode.ERR_PARAM;
import static com.shorten.common.code.BaseCode.SUCCESS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShortenUrlControllerTest extends AbstractMockController<ShortenUrlController> {
    @InjectMocks private ShortenUrlController shortenUrlController;
    @Mock private ShortenUrlService shortenUrlService;

    ShortenUrlControllerInterface is = new ShortenUrlControllerInterface();
    static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ShortenUrlController getController() {
        return shortenUrlController;
    }

    private ShortenUrlReq req = new ShortenUrlReq();
    private LongUrlReq longReq = new LongUrlReq();

    @Test
    public void convert_url_valid_param() throws Exception {
        //given
        req.setLongUrl("HTTPS://www.musinsa.com");

        //when
        when(shortenUrlService.convertShortenUrl(req.getLongUrl())).thenReturn(new String("dkfdjkdfj"));
        MockHttpServletResponse response = is.convertUrl(mockMvc, req);


        //then
        SUCCESS(response);
        verify(shortenUrlService, times(1)).convertShortenUrl(req.getLongUrl());
    }

    @Test
    public void convert_url_inValid_param() throws Exception {
        //given
        req.setLongUrl("naver.com");

        //when
        when(shortenUrlService.convertShortenUrl(req.getLongUrl())).thenReturn(new String("dkfdjkdfj"));
        MockHttpServletResponse response = is.convertUrl(mockMvc, req);

        //then
        ERR_PARAM(response);
        verify(shortenUrlService, times(0)).selectLongUrl(req.getLongUrl());
    }

    @Test
    public void get_long_url_invalid_param() throws Exception {
        //given
        longReq.setShortUrl("djkdfjk");

        //when
        when(shortenUrlService.selectLongUrl(longReq.getShortUrl())).thenReturn(new DefaultHttpResponse<>(BaseCode.SUCCESS));

        MockHttpServletResponse response = is.getLongUrl(mockMvc, longReq);

        //then
        ERR_PARAM(response);
        verify(shortenUrlService,times(0)).selectLongUrl(longReq.getShortUrl());

    }

    public static DefaultHttpResponse<?> SUCCESS(MockHttpServletResponse response) throws Exception{
        return assertion(response, BaseCode.SUCCESS);
    }

    public static DefaultHttpResponse<?> ERR_PARAM(MockHttpServletResponse response) throws Exception{
        return assertion(response, BaseCode.ERR_PARAM);
    }

    public static DefaultHttpResponse<?> assertion(MockHttpServletResponse response, BaseCode responseCode) throws Exception{
        DefaultHttpResponse<?> baseResponse = objectMapper.readValue(response.getContentAsString(), DefaultHttpResponse.class);

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(baseResponse.getReturnMessage(), is(responseCode.message()));
        assertThat(baseResponse.getReturnCode(), is(responseCode.code()));

        return baseResponse;
    }
}