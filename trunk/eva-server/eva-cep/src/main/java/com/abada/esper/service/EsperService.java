/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.service;

import ca.uhn.hl7v2.model.Message;
import com.abada.esper.EsperLoader;
import com.abada.esper.configuration.model.Statement;
import com.abada.esper.configuration.model.Statements;
import com.abada.esper.lock.service.LockService;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
import com.thoughtworks.xstream.XStream;
import java.net.URL;
import java.util.List;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author mmartin
 */
public class EsperService {

    private EsperLoader loader;
    private EPRuntime runtime;
    private LockService lockService;
    
    public EsperService() {
    }

    public EsperService(URL url, EsperLoader loader, LockService lockService) throws Exception {
        
        Statements statements = this.getConfiguration(url);
        this.loader = loader;
        this.lockService = lockService;
        this.runtime = this.loader.getEPRuntime();
        this.loadStatements(statements);
        if(!lockService.isLastLocked()){
            lockService.addLock();
        }else{
            this.recover();
        }
        
    }

    public boolean canSend(){
        if(lockService.isLocked()) return false;
        return true;
    }
    
    public void send(Message message) {
        
        runtime.sendEvent(message);

    }

    private Statements getConfiguration(URL url) {
        XStream x = new XStream();
        x.processAnnotations(Statements.class);
        Statements s = (Statements) x.fromXML(url);

        return s;
    }

    private void loadStatements(Statements statements) throws Exception {

        List<Statement> ls = statements.getStatements();
        EPStatement stmt = null;
        for (Statement s : ls) {
            EPAdministrator adm = loader.getEPAdministrator();
            stmt = adm.createEPL(s.getEPL());

            for (String l : s.getListeners()) {
                stmt.addListener(this.createListener(l));
            }
            stmt.start();
            //TODO add subscribers
        }

    }

    private UpdateListener createListener(String l) throws Exception {

        UpdateListener listener = (UpdateListener) Class.forName(l).newInstance();

        return listener;
        
    }
    
    public void finalice(){
        lockService.openLock();
    }

    @Async
    private void recover() {
        this.lockService.openLock();
        
        System.out.println("RECUPERANDO!!!!!!");
        
        this.lockService.addLock();
        
    }
}
