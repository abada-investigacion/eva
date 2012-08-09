/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao.impl;

import com.abada.eva.historic.dao.HistoricDao;
import com.abada.eva.historic.entities.HistoricEvent;
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
        
        return null;
    }
}
