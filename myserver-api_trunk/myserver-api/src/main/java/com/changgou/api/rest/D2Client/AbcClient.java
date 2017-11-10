package com.changgou.api.rest.D2Client;

import com.linkedin.d2.balancer.D2Client;
import com.linkedin.d2.balancer.D2ClientBuilder;
import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.*;
import nam.e.spa.ce.Abc;
import nam.e.spa.ce.AbcRequestBuilders;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class AbcClient {

    D2Client d2Client = new D2ClientBuilder()
            .setZkHosts("localhost:2181")
            .setZkSessionTimeout(5000, TimeUnit.MILLISECONDS)
            .setZkStartupTimeout(5000, TimeUnit.MILLISECONDS)
            .setLbWaitTimeout(5000, TimeUnit.MILLISECONDS)
            .setFlagFile("/tmp/suppressZkFlag")
            .setBasePath("/d2")
            .setFsBasePath("/tmp/backup")
            .build();
    RestClient _restClient = new RestClient(d2Client, "d2://");


    public Abc get(String longUrl) throws RemoteInvocationException {
        GetRequest<Abc> request = new AbcRequestBuilders()
                .get()
                .id(longUrl)
                .build();
        Abc abc = _restClient.sendRequest(request).getResponse().getEntity();
        return abc;
    }

    public void insert(Abc abc) throws RemoteInvocationException {
        CreateIdRequest<String, Abc> createIdRequest = new AbcRequestBuilders()
                .create()
                .input(abc)
                .build();
        _restClient.sendRequest(createIdRequest).getResponse();
    }

    public void delete(String longUrl) throws RemoteInvocationException {
        DeleteRequest<Abc> deleteRequest = new AbcRequestBuilders().delete()
                .id(longUrl)
                .build();
        _restClient.sendRequest(deleteRequest).getResponse();
    }

    public void update(String longUrl, Abc abc) throws RemoteInvocationException {
        UpdateRequest<Abc> updateRequest = new AbcRequestBuilders()
                .update()
                .id(longUrl)
                .input(abc)
                .build();
        _restClient.sendRequest(updateRequest).getResponse();
    }
}