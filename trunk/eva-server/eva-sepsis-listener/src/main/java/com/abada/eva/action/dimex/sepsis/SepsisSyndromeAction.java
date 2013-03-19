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
public class SepsisSyndromeAction implements DimexAction<SepsisSyndrome>, SetEsperService{

    private EsperService cep;
    
    
    public void doIt(SepsisSyndrome resultFromDimex, Map<String, Object> data) {
        
        //SepsisSyndrome ss = new SepsisSyndrome((Boolean)resultFromDimex.get("result"), data);
        
        resultFromDimex.setProperties(data);
       /* if(cep.canSend()){
            //cep.
        }*/
        
        if(resultFromDimex.getResult()){
            cep.send(resultFromDimex);
        }
    }

    public void setEsperService(EsperService esperService) {
        cep=esperService;
    }

    public EsperService getEsperService() {
        return cep;
    }
    
    
    
}
