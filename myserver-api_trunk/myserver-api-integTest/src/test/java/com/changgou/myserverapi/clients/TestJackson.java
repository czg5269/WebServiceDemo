package com.changgou.myserverapi.clients;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestJackson {
    private static final ObjectMapper _MAPPER = new ObjectMapper()
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    static class Xyz {
        int a;
        int b;
        int sum;
        int product;
    }

    @Test
    public void testObjectToJson() throws JsonProcessingException {
        Xyz xyz = new Xyz();
        xyz.a = 2;
        xyz.b = 5;
        xyz.sum = 8;
        xyz.product = 9;
        String jsonData = _MAPPER.writeValueAsString(xyz);
        System.out.println(jsonData);
    }

    @Test
    public void testJsonToObject() throws IOException {
        String jsonString = "{\"a\":1,\"b\":5,\"sum\":7,\"product\":5}";
        Xyz xyz = _MAPPER.readValue(jsonString, Xyz.class);
        Assert.assertEquals(xyz.b, 5);
        Assert.assertEquals(xyz.sum, 7);
        Assert.assertEquals(xyz.product, 5);
    }
}
