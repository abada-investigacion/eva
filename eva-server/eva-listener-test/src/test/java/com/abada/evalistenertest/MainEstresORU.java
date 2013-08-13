/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.evalistenertest;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
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
public class MainEstresORU {

    public static void main(String[] args) throws Exception {

        ExecutorService e = Executors.newFixedThreadPool(10);
        
        File get = new File("/home/jesus/NetBeansProjects/Investigacion/eva/trunk/eva-server/eva-listener-test/src/main/resources/com/abada/eva/test/hl7/messages/ORU_R01_SinNHC.xml");
        String content = FileUtils.readFileToString(get);
        int i = 0;
        
        for (i = 1; i <= 2000; i++) {
            e.submit(new SendCallback(content.replaceAll("idmensajehl7", i + ""), i));
            Thread.currentThread().sleep(200);
        }
        e.shutdown();
        e.awaitTermination(23, TimeUnit.DAYS);
        
        System.out.println("FINN!!!!!!!!!!!!!!!!!!!!");
        
    }

    
}
