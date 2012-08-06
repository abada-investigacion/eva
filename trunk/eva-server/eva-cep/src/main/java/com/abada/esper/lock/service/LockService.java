/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.service;

import com.abada.esper.lock.dao.LockDao;
import com.abada.esper.lock.entities.Lock;

import javax.annotation.Resource;

/**
 *
 * @author jesus
 */
public class LockService {

    @Resource(name="lockDao")
    private LockDao dao;
    
    private Lock lock;
    
    public Boolean isLastLocked(){
        Lock l = dao.getLastLock();
        if(l != null) return l.getLocked();
        return false;
        
    }
    
    public Boolean isLocked(){
        
        if(lock == null) return false;
        return true;
        
    }
    
    public void addLock(){
        
        this.lock = dao.addLock(new Lock());
        
    }
    
    public void openLock(){
        if(lock == null){
            
            Lock last = dao.getLastLock();
            if(last != null) dao.openLock(last.getId());
            
        } else dao.openLock(lock.getId());
        
        lock = null;
        
    }
}
