/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.historic.dao;

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
