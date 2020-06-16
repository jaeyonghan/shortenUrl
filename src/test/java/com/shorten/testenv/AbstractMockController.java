package com.shorten.testenv;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractMockController<T> {

     protected ObjectMapper objectMapper;
     protected MockMvc mockMvc;

     @Before
     public void setUp(){
         objectMapper = new ObjectMapper();

         this.mockMvc = MockMvcBuilders
                 .standaloneSetup(getController())
                 .addInterceptors()
                 .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                 .build();
     }

     public abstract T getController();
}
