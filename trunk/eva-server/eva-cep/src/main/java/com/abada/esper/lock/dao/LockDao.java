/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.dao;

import com.abada.esper.lock.entities.Lock;


/**
 *
 * @author jesus
 */
public interface LockDao {
    
    
    public Lock getLastLock();
    
    public Lock addLock(Lock lock);
    
    public Lock openLock(Long lock); 
    
}
