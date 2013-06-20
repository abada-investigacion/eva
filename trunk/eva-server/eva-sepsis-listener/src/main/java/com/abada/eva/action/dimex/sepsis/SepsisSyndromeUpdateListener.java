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
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author jesus
 */
public class SepsisSyndromeUpdateListener extends AbstractDimexUpdateListener<SepsisSyndrome> {
    
    private String url;
    private Map<String, String> symptoms;
    private List<String> dimexValues;
    private static final Log logger = LogFactory.getLog(SepsisSyndromeUpdateListener.class);
    
    public SepsisSyndromeUpdateListener() {
        super();
    }
    
    @Override
    protected Map<String, Object> getData(Object[] oldMessages, Object[] newMessages) {
        
        Map<String, Object> values = new HashMap<String, Object>();
        try {
            for (Object event : newMessages) {
                if (event instanceof ORU_R01Custom) {
                    addORUValues((ORU_R01Custom) event, values);
                } else if (event instanceof CDABean) {
                    this.addCDAValues(values, (CDABean) event);
                }
            }
        } catch (Exception e) {
            logger.error(e);
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
    
    private void addORUValues(ORU_R01Custom oru, Map<String, Object> values) {
        
        Map<String, Object> oruv = oru.getSymptons();
        
        for (String v : dimexValues) {
            
            if (oruv.containsKey(v)) {
                values.put(v, oruv.get(v));
            }
            
        }
        
    }
    
    private void addCDAValues(Map<String, Object> values, CDABean cdaBean) {
        
        for (DatoBean dat : cdaBean.getDatos()) {
            for (CodificacionBean cb : dat.getCodigos()) {
                if (symptoms.containsKey(cb.getCode() + cb.getCodeSystem())) {
                    
                    if((cb.getCode() + cb.getCodeSystem()).equals(SepsisConstants.TEMPERATURA_CORPORAL_CODE)){
                        values.put(symptoms.get(cb.getCode() + cb.getCodeSystem()), getBodyTemp(dat.getDato()));
                    }else{
                        values.put(symptoms.get(cb.getCode() + cb.getCodeSystem()), dat.getDato());
                    }
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
    
    public List<String> getDimexValues() {
        return dimexValues;
    }
    
    public void setDimexValues(List<String> dimexValues) {
        this.dimexValues = dimexValues;
    }

    private String getBodyTemp(String dat) {
        
        Pattern p = Pattern.compile("\\d\\d?([\\.|\\,](\\d\\d?))?");
        Matcher m = p.matcher(dat);        
        if (m.find()){
            return m.group().replace(',', '.');
        }
        return null;        
    }
}
