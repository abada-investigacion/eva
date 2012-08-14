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
    /**
     * Return the last session lock
     * @return 
     */
    public Lock getLastLock();
    /**
     * Create a new lock session
     * @param lock
     * @return 
     */
    public Lock addLock(Lock lock);
    /**
     * Open and remove the lock
     * @param lock
     * @return 
     */
    public Lock openLock(Long lock); 
    
}
