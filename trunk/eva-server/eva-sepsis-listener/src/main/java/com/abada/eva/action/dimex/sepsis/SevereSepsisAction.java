/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import com.abada.eva.action.dimex.DimexAction;
import com.abada.eva.api.EsperService;
import com.abada.eva.api.SetEsperService;
import java.util.Map;

/**
 *
 * @author jesus
 */
public class SevereSepsisAction implements DimexAction<SevereSepsis>, SetEsperService{

    private EsperService cep;
    
    public void doIt(SevereSepsis resultFromDimex, Map<String, Object> data) {
        
        resultFromDimex.setProperties(data);
        
       
        
       // cep.send(resultFromDimex);
        
    }

    public void setEsperService(EsperService esperService) {
        cep=esperService;
    }

    public EsperService getEsperService() {
        return cep;
    }
}
