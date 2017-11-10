package com.changgou.api.rest.services;

import com.changgou.api.rest.R2Client.AbcClient;
import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.restli.common.HttpStatus;
import com.linkedin.restli.server.CreateResponse;
import com.linkedin.restli.server.UpdateResponse;
import nam.e.spa.ce.Abc;
import org.springframework.stereotype.Component;

@Component("abcService")//compile springContext要放在这个package的gradle build中
public class AbcService {
    private AbcClient _abcClient;

    public AbcService(AbcClient abcClient) {
        _abcClient = abcClient;
    }

    public Abc get(String longUrl) throws RemoteInvocationException {
        Abc abc = _abcClient.get(longUrl);
        return abc;
    }

    public CreateResponse create(Abc abc) throws RemoteInvocationException {
        _abcClient.insert(abc);
        return new CreateResponse(HttpStatus.S_201_CREATED);
    }

    public UpdateResponse delete(String longUrl) throws RemoteInvocationException {
        _abcClient.delete(longUrl);
        return new UpdateResponse(HttpStatus.S_200_OK);
    }


    public UpdateResponse update(String longUrl, Abc abc) throws RemoteInvocationException {
        _abcClient.update(longUrl, abc);
        return new UpdateResponse(HttpStatus.S_200_OK);
    }
}
