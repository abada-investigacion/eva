/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.dao;

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


import com.abada.esper.lock.entities.Lock;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author jesus
 */

public class LockDaoImpl implements LockDao{

    @PersistenceContext(unitName = "eva-CepPU")
    private EntityManager entityManager;
    
    /**
     * Return the last lock
     * @return 
     */
    @Transactional(value = "evaCepService-txm",readOnly=true)
    public Lock getLastLock() {
        Query q = entityManager.createQuery("select l from Lock l order by l.start desc").setFirstResult(0).setMaxResults(1);
        
        List<Lock> l= q.getResultList();        
        if(l.size() > 0) {
            return l.get(0);
        }        
        return null;
    }
    
    @Transactional(value = "evaCepService-txm", rollbackFor = {Exception.class})
    public Lock addLock(Lock lock) {        
        entityManager.persist(lock);        
        return lock;
    }
    
    @Transactional(value = "evaCepService-txm", rollbackFor = {Exception.class})
    public Lock openLock(Long lockid) {
        Lock lock = entityManager.find(Lock.class, lockid);
        lock.setLocked(false);
        lock.setEnd(System.currentTimeMillis());
        
        return lock;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }    
}
