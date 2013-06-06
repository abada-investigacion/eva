/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import com.abada.eva.action.dimex.AbstractDimexUpdateListener;
import es.sacyl.eva.beans.CDABean;
import es.sacyl.eva.beans.CodificacionBean;
import es.sacyl.eva.beans.DatoBean;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jesus
 */
public class SepsisSyndromeUpdateListener extends AbstractDimexUpdateListener<SepsisSyndrome> {

    private String url;
    private Map<String, String> symptoms;

    public SepsisSyndromeUpdateListener() {
        super();
    }

    @Override
    protected Map<String, Object> getData(Object[] oldMessages, Object[] newMessages) {

        Map<String, Object> values = new HashMap<String, Object>();

        for (Object event : newMessages) {
            if (event instanceof ORU_R01Custom) {
                values.putAll(((ORU_R01Custom) event).getSymptons());
            } else if (event instanceof CDABean) {
                this.addCDAValues(values, (CDABean) event);
            }
        }
        return values;
    }

    @Override
    protected URI getUrl(Map<String, Object> data) {
        try {
            return new URI(this.url);
        } catch (URISyntaxException ex) {
        }
        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }

   
    private void addCDAValues(Map<String, Object> values, CDABean cdaBean) {

        for (DatoBean dat : cdaBean.getDatos()) {
            for (CodificacionBean cb : dat.getCodigos()) {
                if (symptoms.containsKey(cb.getCode() + cb.getCodeSystem())) {
                    values.put(symptoms.get(cb.getCode() + cb.getCodeSystem()), dat.getDato());
                }
            }
        }
        values.put(SepsisConstants.NHC, cdaBean.getNhc());
    }

    public Map<String, String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Map<String, String> symptoms) {
        this.symptoms = symptoms;
    }
}
