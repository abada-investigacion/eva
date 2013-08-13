/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.generator.main;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 *
 * @author david HttpThread
 */


public class HttpThread implements Runnable
{
    private static ThreadSafeClientConnManager cm;
    private static DefaultHttpClient httpClient;
    static
    {
        
        cm = new ThreadSafeClientConnManager();
        cm.setDefaultMaxPerRoute(9999);
        cm.setMaxTotal(9999);
        httpClient = new DefaultHttpClient(cm );
        
         httpClient.getCredentialsProvider().setCredentials(
                    new AuthScope(AuthScope.ANY),
                    new UsernamePasswordCredentials("admin", "admin"));
    }

    private String id;
    private String message;


    public HttpThread( String id, String message )
    {
        this.id= id;
        this.message = message;
    }


    @Override
    public void run()
    {       
        long start = System.currentTimeMillis();

           HttpPost post = new HttpPost( "http://localhost:8080/eva-rest/rs/sendmessage" );            

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add( new BasicNameValuePair("hl7", message));
           
            try
            {
                
                post.setEntity( new UrlEncodedFormEntity( nvps, HTTP.UTF_8 ) );

           
                HttpResponse response = httpClient.execute( post );
                
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
            finally
            {

            }
        

        long stop = System.currentTimeMillis();
        System.out.println( this.id + "  finished: " + (stop - start) + " ms" );        
    }
}




















