/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

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
