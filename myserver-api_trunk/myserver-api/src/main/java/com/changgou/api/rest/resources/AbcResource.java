package com.changgou.api.rest.resources;

import com.changgou.api.rest.services.AbcService;
import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.restli.common.HttpStatus;
import com.linkedin.restli.server.CreateResponse;
import com.linkedin.restli.server.RestLiServiceException;
import com.linkedin.restli.server.UpdateResponse;
import com.linkedin.restli.server.annotations.RestLiCollection;
import com.linkedin.restli.server.resources.CollectionResourceTemplate;
import nam.e.spa.ce.Abc;

import javax.inject.Inject;
import javax.inject.Named;

@RestLiCollection(name = "abc", namespace = "nam.e.spa.ce")
public class AbcResource extends CollectionResourceTemplate<String, Abc> {
    //private AbcService _abcService = new AbcService(new AbcClient());
    //private AbcService _abcService = AbcServiceFactory.createInstance();

    @Inject @Named("abcService")
    private AbcService _abcService;

    @Override
    public Abc get(String longUrl){
        //Abc res = new Abc();
        try {
            return _abcService.get(longUrl);
        } catch (RemoteInvocationException e) {
            throw new RestLiServiceException(HttpStatus.S_500_INTERNAL_SERVER_ERROR);
        }
        //return res;
    }

    @Override
    public CreateResponse create(Abc abc) {
        CreateResponse res = new CreateResponse(HttpStatus.S_500_INTERNAL_SERVER_ERROR);
        try {
            res = _abcService.create(abc);
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public UpdateResponse delete(String longUrl) {
        UpdateResponse res = new UpdateResponse(HttpStatus.S_500_INTERNAL_SERVER_ERROR);
        try {
            res = _abcService.delete(longUrl);
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public UpdateResponse update(String longUrl, Abc abc) {
        UpdateResponse res = new UpdateResponse(HttpStatus.S_500_INTERNAL_SERVER_ERROR);
        try {
            res = _abcService.update(longUrl, abc);
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
        }
        return res;
    }
}
