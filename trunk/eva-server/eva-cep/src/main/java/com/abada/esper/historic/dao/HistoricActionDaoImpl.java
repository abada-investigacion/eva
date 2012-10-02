/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.historic.dao;

import com.abada.esper.historic.entities.HistoricAction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmartin
 */
public class HistoricActionDaoImpl implements HistoricActionDao {

    @PersistenceContext(unitName = "eva-CepPU")
    private EntityManager entityManager;

    @Transactional(value = "evaCepService-txm", rollbackFor = {Exception.class})
    public void persistActionInput(HistoricAction h) {
        entityManager.persist(h);
    }
}
