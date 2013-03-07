/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katsu
 */
public abstract class AbstractDimexUpdateListener<T> extends AbstractDimex<T> implements UpdateListener {       

    public AbstractDimexUpdateListener() {
        super();
    }

    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        Object [] newE=create(newEvents);
        Object [] oldE=create(oldEvents);
        
        this.doItPriv(oldE,newE);
    }      
    
    private Object[] create(EventBean[] events) {
        if (events == null) {
            return null;
        }
        Object aux;
        List<Object> result = new ArrayList<Object>();
        for (int i = 0; i < events.length; i++) {
            aux = events[i].getUnderlying();
            result.add(aux);
        }
        return result.toArray(new Object[0]);
    }
}
