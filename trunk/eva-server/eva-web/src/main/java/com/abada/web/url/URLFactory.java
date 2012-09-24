/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.web.url;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 *
 * @author katsu
 */
public class URLFactory {
    private MessageSource messageSource;
    
    private URL serverUrl;
    private String serverUrlRole;

    public synchronized String getServerUrlRole() {
        if (serverUrlRole==null)
            serverUrlRole=messageSource.getMessage("urlServerRoles", null, Locale.getDefault());
        return serverUrlRole;
    }

    public void setServerUrlRole(String serverUrlRole) {
        this.serverUrlRole = serverUrlRole;
    }

    public synchronized  URL getServerUrl() throws MalformedURLException {      
        if (serverUrl==null){
            serverUrl=new URL(messageSource.getMessage("urlServer", null, Locale.getDefault()));
        }
        return serverUrl;
    }

    public void seterverUrl(URL serverUrl) {
        this.serverUrl = serverUrl;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
}
