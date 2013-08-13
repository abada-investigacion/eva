/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.dao.impl;

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

import com.abada.eva.historic.dao.HistoricDao;
import com.abada.eva.historic.entities.HistoricEvent;
import com.abada.eva.historic.entities.HistoricGenericEvent;
import com.abada.eva.historic.entities.HistoricGenericEventContainer;
import com.abada.json.Json;
import java.util.ArrayList;
import java.util.List;
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

    @Resource(name = "jsonSerializer")
    private Json json;
    
    @Transactional(value = "evaHistoricService-txm", rollbackFor = {Exception.class})
    public HistoricEvent persistHistoricEvent(HistoricEvent h) {
        entityManager.persist(h);
        return h;
    }
    
    public Long getHistoricEventCount(){
        Long result =(Long)entityManager.createQuery("select count(h) from HistoricEvent h").getSingleResult();
        return result;
    }

    public List<HistoricEvent> getHistoricEvents(long index, long max) {
        List<HistoricEvent> result =entityManager.createQuery("select h from HistoricEvent h order by h.run asc").setFirstResult((int)index).setMaxResults((int)max).getResultList();
        return result;
    }

    public HistoricGenericEvent persistHistoricGenericEvent(HistoricGenericEvent h,Object obj) {
        h.setTrace(json.serialize(obj));
        entityManager.persist(h);
        return h;
    }

    public Long getHistoricGenericEventCount() {
        Long result =(Long)entityManager.createQuery("select count(h) from HistoricGenericEvent h").getSingleResult();
        return result;
    }

    public List<HistoricGenericEventContainer> getHistoricGenericEvent(long index, long max) throws Exception{
        List<HistoricGenericEvent> list =entityManager.createQuery("select h from HistoricGenericEvent h order by h.run asc").setFirstResult((int)index).setMaxResults((int)max).getResultList();
        if (list!=null){
            List<HistoricGenericEventContainer> result=new ArrayList<HistoricGenericEventContainer>();                   
            for (HistoricGenericEvent e:list){
                HistoricGenericEventContainer aux=new HistoricGenericEventContainer();
                aux.setEvent(e);
                Object obj=json.deserialize(e.getTrace(), this.getClass().getClassLoader().loadClass(e.getType()));
                aux.setDeserializedObject(obj);
                result.add(aux);
            }
            return result;
        }
        return null;
    }
}
