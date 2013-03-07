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
            if (aux instanceof EventBean) {
                result.addAll(createPriv((EventBean) aux));
            } else {
                result.add(aux);
            }
        }
        return result.toArray(new Object[0]);
    }

    private List<Object> createPriv(EventBean bean) {
        List<Object> result = new ArrayList<Object>();
        Object aux = bean.getUnderlying();
        if (aux instanceof Map) {
            Map m = (Map) aux;
            for (Object obj : m.values()) {
                if (obj instanceof EventBean) {
                    result.addAll(createPriv((EventBean) obj));
                } else {
                    result.add(obj);
                }
            }
        }else{
            result.add(aux);
        }
        return result;
    }
}
