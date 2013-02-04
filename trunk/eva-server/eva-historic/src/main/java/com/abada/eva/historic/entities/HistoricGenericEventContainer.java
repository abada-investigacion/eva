/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.entities;

/**
 *
 * @author katsu
 */
public class HistoricGenericEventContainer {
    private HistoricGenericEvent event;
    private Object deserializedObject;

    public HistoricGenericEvent getEvent() {
        return event;
    }

    public void setEvent(HistoricGenericEvent event) {
        this.event = event;
    }

    public Object getDeserializedObject() {
        return deserializedObject;
    }

    public void setDeserializedObject(Object deserializedObject) {
        this.deserializedObject = deserializedObject;
    }        
}
