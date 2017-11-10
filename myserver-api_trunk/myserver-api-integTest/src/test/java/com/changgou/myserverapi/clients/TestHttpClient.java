package com.changgou.myserverapi.clients;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestHttpClient {

    @Test
    public void test() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        //get
        String getUrl = "http://localhost:7077/myserver-backend/xyz/numA=1&numB=5";
        HttpGet httpGet = new HttpGet(getUrl);
        HttpResponse responseGet = httpClient.execute(httpGet);
        httpGet.releaseConnection();
        String result = EntityUtils.toString(responseGet.getEntity(), "UTF-8");
        //System.out.println(result);

        //post + get
        String postUrl = "http://localhost:7077/myserver-backend/xyz";
        HttpPost httpPost = new HttpPost(postUrl);
        String jsonPost = "{\"numA\":1,\"numB\":5, \"sum\":8}";
        StringEntity entityPost = new StringEntity(jsonPost);
        httpPost.setEntity(entityPost);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse responsePost = httpClient.execute(httpPost);
        httpPost.releaseConnection();

        HttpGet httpGetAfterPost = new HttpGet(getUrl);
        HttpResponse responseAfterPost = httpClient.execute(httpGetAfterPost);
        httpGet.releaseConnection();
        String resultAfterPost = EntityUtils.toString(responseAfterPost.getEntity(), "UTF-8");
        //System.out.println(resultAfterPost);

        //put + get
        String url = "http://localhost:7077/myserver-backend/xyz/numA=1&numB=5";
        HttpPut httpPut = new HttpPut(url);
        String jsonPut = "{\"numA\":1,\"numB\":5, \"sum\":18}";
        HttpEntity entityPut = new StringEntity(jsonPut);
        httpPut.setEntity(entityPut);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        HttpResponse responsePut = httpClient.execute(httpPut);
        httpPut.releaseConnection();

        HttpGet httpGetAfterPut = new HttpGet(getUrl);
        HttpResponse responseGetAfterPut = httpClient.execute(httpGetAfterPut);
        httpGet.releaseConnection();
        String resultAfterPut = EntityUtils.toString(responseGetAfterPut.getEntity(), "UTF-8");
        //System.out.println(resultAfterPut);

        //delete + get
        String deleteUrl = "http://localhost:7077/myserver-backend/xyz/numA=1&numB=5";
        HttpDelete httpDelete = new HttpDelete(deleteUrl);
        HttpResponse responseDelete = httpClient.execute(httpDelete);
        httpDelete.releaseConnection();

        HttpGet httpGetAfterDelete = new HttpGet(getUrl);
        HttpResponse responseGetAfterDelete = httpClient.execute(httpGetAfterDelete);
        httpGet.releaseConnection();
        String resultAfterDelete = EntityUtils.toString(responseGetAfterDelete.getEntity(), "UTF-8");
        System.out.println(resultAfterDelete);
    }
}