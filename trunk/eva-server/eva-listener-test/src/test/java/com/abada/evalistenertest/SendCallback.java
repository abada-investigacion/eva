/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author jesus
 */
public class SendCallback implements Callable {

    private String content;
    private int i;

    public SendCallback(String c, int i) {
        content = c;
        this.i = i;
    }

    public Object call() throws Exception {
        sendHl7(content);
        System.out.println("Enviado " + i );
        return null;
    }
    private final Log logger = LogFactory.getLog(MainEstresORU.class);
    private final String urlhl7 = "http://192.168.1.35:8080/eva-rest/rs/sendmessage";
    /**
     * URLCDA DEBE TENER LA MISMA IP QUE URLHL7*
     */
    private final String urlcda = "http://192.168.1.35:8080/eva-rest/rs/send";
    private final String path = "/home/mmartin/NetBeansProjects";
    private DefaultHttpClient httpclient;
    private HttpResponse httpResponse = null;
    private HttpEntity httpEntity = null;
    private List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    private Gson json = new GsonBuilder().create();

    private void sendHl7(String message) throws Exception {
        nvps.add(new BasicNameValuePair("hl7", message));
        HttpPost httpPost = new HttpPost(urlhl7);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        sendMessage(httpPost);
        Thread.sleep(5000);
        nvps.clear();
    }

    private void sendMessage(HttpPost httpPost) throws Exception {
        httpclient = new DefaultHttpClient();

        try {

            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope(AuthScope.ANY),
                    new UsernamePasswordCredentials("admin", "admin"));
            httpResponse = httpclient.execute(httpPost);
            httpEntity = httpResponse.getEntity();

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                logger.trace(httpResponse.getStatusLine().toString());


            } else {
                throw new Exception(httpResponse.getStatusLine().getStatusCode() + " Http code response.");
            }
        } catch (Exception e) {
            if (httpEntity != null) {
                logger.error(e);
            } else {
                logger.error(e);
            }
            throw e;
        } finally {
            if (httpEntity != null) {
                httpEntity.getContent().close();
            }
        }
    }
}
