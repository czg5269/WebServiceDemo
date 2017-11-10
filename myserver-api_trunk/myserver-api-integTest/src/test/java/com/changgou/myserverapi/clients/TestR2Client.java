package com.changgou.myserverapi.clients;

import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.*;
//import nam.e.spa.ce.Abc;
import nam.e.spa.ce.Abc;
import nam.e.spa.ce.AbcRequestBuilders;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class TestR2Client {
    HttpClientFactory http = new HttpClientFactory();
    TransportClientAdapter r2Client = new TransportClientAdapter(
            http.getClient(Collections.<String, String>emptyMap()));
    RestClient _restClient = new RestClient(r2Client, "http://localhost:7077/myserver-backend/");

    @Test
    public void testABC() throws RemoteInvocationException {
        final String longUrl = "asdf";
        // get
        GetRequest<Abc> request = new AbcRequestBuilders()
                .get()
                .id(longUrl)
                .build();
        Abc abc = _restClient.sendRequest(request).getResponse().getEntity();
        Assert.assertEquals(abc, new Abc().setLongUrl("asdf").setShortUrl("fdsa"));
        //System.out.println(abc.getLongUrl());

        // create + get
        CreateIdRequest<String, Abc> createIdRequest = new AbcRequestBuilders()
                .create()
                .input(new Abc().setLongUrl(longUrl).setShortUrl("zxcv"))
                .build();
        _restClient.sendRequest(createIdRequest).getResponse();

        GetRequest<Abc> getAfterCreate = new AbcRequestBuilders()
                .get()
                .id(longUrl)
                .build();
        Abc abcAfterCreate = _restClient.sendRequest(getAfterCreate).getResponse().getEntity();
        Assert.assertEquals(abcAfterCreate, new Abc().setLongUrl("asdf").setShortUrl("zxcv"));

        // update + get
        UpdateRequest<Abc> updateRequest = new AbcRequestBuilders()
                .update()
                .id(longUrl)
                .input(new Abc().setLongUrl(longUrl).setShortUrl("aaaa"))
                .build();
        _restClient.sendRequest(updateRequest).getResponse();

        GetRequest<Abc> getAfterUpdate = new AbcRequestBuilders()
                .get()
                .id(longUrl)
                .build();
        Abc abcAfterUpdate = _restClient.sendRequest(getAfterUpdate).getResponse().getEntity();
        Assert.assertEquals(abcAfterUpdate, new Abc().setLongUrl("asdf").setShortUrl("aaaa"));

        // delete + get
        DeleteRequest<Abc> deleteRequest = new AbcRequestBuilders().delete()
                .id(longUrl)
                .build();
        _restClient.sendRequest(deleteRequest).getResponse();

        GetRequest<Abc> getAfterDelete = new AbcRequestBuilders()
                .get()
                .id(longUrl)
                .build();
        Abc abcAfterDelete = _restClient.sendRequest(getAfterDelete).getResponse().getEntity();
        Assert.assertEquals(abcAfterDelete, new Abc().setLongUrl("asdf").setShortUrl("fdsa"));
    }

}
