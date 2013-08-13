/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

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

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author katsu
 */
public abstract class AbstractDimexUpdateListener<T> extends AbstractDimex<T> implements UpdateListener {

    public AbstractDimexUpdateListener() {
        super();
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        Object[] newE = create(newEvents);
        Object[] oldE = create(oldEvents);

        this.doItPriv(oldE, newE);
    }

    private Object[] create(EventBean[] events) {
        if (events == null) {
            return null;
        }
        Object aux;
        List<Object> result = new ArrayList<Object>();
        for (int i = 0; i < events.length; i++) {
            aux = events[i].getUnderlying();
            if (aux != null && Map.class.isInstance(aux)) {
                result.addAll(createPriv((Map) aux));
            } else {
                if (aux instanceof EventBean) {
                    result.add(((EventBean) aux).getUnderlying());
                } else {
                    result.add(aux);
                }
            }
        }
        return result.toArray(new Object[0]);
    }

    private List<Object> createPriv(Map map) {
        List<Object> result = new ArrayList<Object>();

        for (Object obj : map.values()) {
            if (obj instanceof EventBean) {
                EventBean aux = (EventBean) obj;
                if (aux.getUnderlying() instanceof Map) {
                    result.addAll(createPriv((Map) aux.getUnderlying()));
                } else {
                    if (obj instanceof EventBean) {
                        result.add(((EventBean) obj).getUnderlying());
                    } else {
                        result.add(obj);
                    }
                }
            } else {
                if (obj instanceof EventBean) {
                    result.add(((EventBean) obj).getUnderlying());
                } else {
                    result.add(obj);
                }
            }
        }

        return result;
    }
}
