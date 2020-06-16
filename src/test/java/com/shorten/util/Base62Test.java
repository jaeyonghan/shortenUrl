package com.shorten.util;

import com.shorten.common.util.Base62;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Base62Test {

    Base62 base62 = new Base62();

    @Test
    public void testEncodeAndDecode() throws Exception{
        String encodeValue = base62.encode(1234512345);
        assertEquals(1234512345, base62.decode(encodeValue));
    }

    @Test
    public void testRandomEncodeAndDecode() throws Exception{
        for(int i=0; i < 10; i++) {
            Random rnd = new Random();
            long value = Math.abs(rnd.nextLong() % 10000000000L);
            String encodeValue = base62.encode(value);
            assertEquals(value, base62.decode(encodeValue));
        }
    }
}
