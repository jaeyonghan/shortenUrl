package com.shorten.testenv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shorten.controller.ShortenUrlController;
import com.shorten.model.LongUrlReq;
import com.shorten.model.ShortenUrlReq;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

public class ShortenUrlControllerInterface {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public MockHttpServletResponse convertUrl(MockMvc mockMvc, ShortenUrlReq req) throws Exception {
        String requestBody =  new ObjectMapper().valueToTree(req).toString();

        return mockMvc.perform(post("/shorten")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().handlerType(ShortenUrlController.class))
                .andExpect(handler().methodName("convertShortenUrl"))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    public MockHttpServletResponse getLongUrl(MockMvc mockMvc, LongUrlReq req) throws Exception {
        String requestBody =  new ObjectMapper().valueToTree(req).toString();

        return mockMvc.perform(get("/shorten")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().handlerType(ShortenUrlController.class))
                .andExpect(handler().methodName("getLongUrl"))
                .andDo(print())
                .andReturn()
                .getResponse();
    }
}
