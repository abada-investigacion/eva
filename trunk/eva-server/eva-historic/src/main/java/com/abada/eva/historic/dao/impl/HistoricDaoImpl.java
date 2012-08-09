/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao.impl;

import com.abada.eva.historic.dao.HistoricDao;
import com.abada.eva.historic.entities.HistoricEvent;
import java.util.List;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmartin
 */
public class HistoricDaoImpl implements HistoricDao {

    @PersistenceContext(unitName = "eva-historicPU")
    private EntityManager entityManager;
    @Resource(name="evaDS")
    private DataSource ds;

    @Transactional(value = "evaHistoricService-txm", rollbackFor = {Exception.class})
    public HistoricEvent persistHistoricEvent(HistoricEvent h) {
        entityManager.persist(h);
        return h;
    }
    
    public Long getCount(){
        Long result =(Long)entityManager.createQuery("select count(h) from HistoricEvents h").getSingleResult();
        return result;
    }

    public List<HistoricEvent> getHistoricEvents(long index, long max) {
        List<HistoricEvent> result =entityManager.createQuery("select h from HistoricEvents h").setFirstResult((int)index).setMaxResults((int)max).getResultList();
        return result;
    }
}
