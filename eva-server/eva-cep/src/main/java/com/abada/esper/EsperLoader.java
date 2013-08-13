/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper;

/*
 * #%L
 * Eva
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.espertech.esper.client.*;
import java.net.URL;
import javax.naming.Context;

/**
 * Loader for Esper
 * @author katsu
 */
public class EsperLoader implements EPServiceProvider{
    private EPServiceProvider serviceProvider;

    /**
     * Constructor
     * @param url Configuration file
     */
    public EsperLoader(URL url) {
        Configuration conf=new Configuration();
        conf.configure(url);
        //Override configuration, because is needed to use isolated service
        conf.getEngineDefaults().getViewResources().setShareViews(false);
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
