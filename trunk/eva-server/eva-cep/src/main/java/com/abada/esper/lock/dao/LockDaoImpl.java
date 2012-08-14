/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.dao;


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

    @PersistenceContext(unitName = "eva-LockPU")
    private EntityManager entityManager;
    
    /**
     * Return the last lock
     * @return 
     */
    @Transactional(value = "evaLockService-txm",readOnly=true)
    public Lock getLastLock() {
        Query q = entityManager.createQuery("select l from Lock l order by l.start desc").setFirstResult(0).setMaxResults(1);
        
        List<Lock> l= q.getResultList();        
        if(l.size() > 0) {
            return l.get(0);
        }        
        return null;
    }
    
    @Transactional(value = "evaLockService-txm", rollbackFor = {Exception.class})
    public Lock addLock(Lock lock) {        
        entityManager.persist(lock);        
        return lock;
    }
    
    @Transactional(value = "evaLockService-txm", rollbackFor = {Exception.class})
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
