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
import com.abada.eva.historic.dao.HistoricDao;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
import com.thoughtworks.xstream.XStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author mmartin
 */
public class EsperService {
    /**
     * loader for esper
     */
    private EsperLoader loader;
    /**
     * esper core
     */
    private EPRuntime runtime;

    private LockService lockService;
    private int numMax;
    private ExecutorService es;
    private HistoricDao historicDao;
    
    public EsperService(URL url, EsperLoader loader, LockService lockService, HistoricDao historicDao,  ExecutorService es) throws Exception {
        
        Statements statements = this.getConfiguration(url);
        this.historicDao = historicDao;
        this.es = es;
        this.loader = loader;
        this.lockService = lockService;
        this.runtime = this.loader.getEPRuntime();
        this.loadStatements(statements);
        if(!lockService.isLastLocked()){
            lockService.addNewLock();
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

    //FIXME Do it in other way, when the Action conectp were implemented
    private UpdateListener createListener(String l) throws Exception {

        UpdateListener listener = (UpdateListener) Class.forName(l).newInstance();

        return listener;
        
    }
    
    public void finalice(){
        lockService.releaseLastLock();
        loader.destroy();
    }

    @Async
    private void recover() throws Exception {                
        System.out.println("AÑADIENTO TASKS!!!!"); 
       
        Long total = historicDao.getCount();
        Long n = 1L;
        
        while(n < total){
            this.recoverGroup(n);
        }
        
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        
        
        this.lockService.releaseLastLock();
        this.lockService.addNewLock();
        
    }
    
    @Async(value="recoverExecutor")
    private void recoverGroup(Long first){
        
    }
    
    private long generateFirst(long last ){
  
        return last + numMax;
    }
}
