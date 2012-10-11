/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.historic.dao;

import com.abada.esper.historic.entities.HistoricAction;
import com.abada.springframework.orm.jpa.support.JpaDaoUtils;
import com.abada.springframework.web.servlet.command.extjs.gridpanel.GridRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mmartin
 */
public class HistoricActionDaoImpl extends JpaDaoUtils implements HistoricActionDao {

    @PersistenceContext(unitName = "eva-CepPU")
    private EntityManager entityManager;

    @Transactional(value = "evaCepService-txm", rollbackFor = {Exception.class})
    public void persistActionInput(HistoricAction h) {
        entityManager.persist(h);
    }

    @Transactional(value = "evaCepService-txm", readOnly = true)
    public List<HistoricAction> getAll(GridRequest filters) {
        List<HistoricAction> lha = this.find(entityManager, "select i from HistoricAction i" + filters.getQL("i", true), filters.getParamsValues(), filters.getStart(), filters.getLimit());

        return lha;

    }

    @Transactional(value = "evaCepService-txm", readOnly = true)
    public List<HistoricAction> getid(String id) {
        List<HistoricAction> lha = entityManager.createQuery("select i from HistoricAction i where i.id=?").setParameter(1, id).getResultList();
        return lha;

    }

    @Transactional(value = "evaCepService-txm", readOnly = true)
    public Long loadSizeAll(GridRequest filters) {
        List<Long> result = this.find(entityManager, "select count(*) from HistoricAction i" + filters.getQL("i", true), filters.getParamsValues());
        return result.get(0);
    }
}
