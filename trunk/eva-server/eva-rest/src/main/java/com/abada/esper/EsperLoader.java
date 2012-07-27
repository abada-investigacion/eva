/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper;

import com.espertech.esper.client.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.naming.Context;

/**
 *
 * @author katsu
 */
public class EsperLoader implements EPServiceProvider{
    private EPServiceProvider serviceProvider;

    public EsperLoader(URL url) {
        Configuration conf=new Configuration();
        conf.configure(url);
        
        serviceProvider=EPServiceProviderManager.getDefaultProvider(conf);
    }

    public EPRuntime getEPRuntime() throws EPServiceDestroyedException {
        return serviceProvider.getEPRuntime();
    }

    public EPAdministrator getEPAdministrator() throws EPServiceDestroyedException {
        return serviceProvider.getEPAdministrator();
    }

    public Context getContext() throws EPServiceDestroyedException {
        return serviceProvider.getContext();
    }

    public void initialize() {
        serviceProvider.initialize();
    }

    public String getURI() {
        return serviceProvider.getURI();
    }

    public void destroy() {
        serviceProvider.destroy();
    }

    public boolean isDestroyed() {
        return serviceProvider.isDestroyed();
    }

    public void addServiceStateListener(EPServiceStateListener listener) {
        serviceProvider.addServiceStateListener(listener);
    }

    public boolean removeServiceStateListener(EPServiceStateListener listener) {
        return serviceProvider.removeServiceStateListener(listener);
    }

    public void removeAllServiceStateListeners() {
        serviceProvider.removeAllServiceStateListeners();
    }

    public void addStatementStateListener(EPStatementStateListener listener) {
        serviceProvider.addStatementStateListener(listener);
    }

    public boolean removeStatementStateListener(EPStatementStateListener listener) {
        return serviceProvider.removeStatementStateListener(listener);
    }

    public void removeAllStatementStateListeners() {
        serviceProvider.removeAllStatementStateListeners();
    }

    public EPServiceProviderIsolated getEPServiceIsolated(String name) throws EPServiceDestroyedException {
        return serviceProvider.getEPServiceIsolated(name);
    }

    public String[] getEPServiceIsolatedNames() {
        return serviceProvider.getEPServiceIsolatedNames();
    }        
}
