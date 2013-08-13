/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.service;

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

import com.abada.esper.lock.dao.LockDao;
import com.abada.esper.lock.entities.Lock;

/**
 * Use to know if the last session evirithing was ok, and in the the case something was wrong
 * then the system will recover.
 * 
 * @author jesus
 */
public class LockService {

    /**
     * Dao to access the sessions lock history
     */    
    private LockDao dao;
    /**
     * Current Session lock
     */
    private Lock lock;

    public LockDao getDao() {
        return dao;
    }

    public void setDao(LockDao dao) {
        this.dao = dao;
    }   
    
    /**
     * Return true if the last session was bad (are locked)
     * Return false in other case
     * @return 
     */
    public Boolean isLastLocked() {
        Lock l = dao.getLastLock();
        if (l != null) {
            return l.getLocked();
        }
        return false;
    }

    /**
     * Return true if the current session was bad (are locked)
     * Return false in other case
     * @return 
     */
    public Boolean isLocked() {
        if (lock == null) {
            return false;
        }else{
            return lock.getLocked();
        }
    }

    /**
     * Create a new current session lock
     */
    public void addNewLock() {
        this.lock = dao.addLock(new Lock());
    }

    /**
     * Release, unlock, the current session lock if exist. If not exists
     * a current session lock then take the last in the history and release this.
     */
    public void releaseLastLock() {
        if (lock == null) {
            Lock last = dao.getLastLock();
            if (last != null) {
                dao.openLock(last.getId());
            }
        } else {
            dao.openLock(lock.getId());
        }
        lock = null;
    }
}
