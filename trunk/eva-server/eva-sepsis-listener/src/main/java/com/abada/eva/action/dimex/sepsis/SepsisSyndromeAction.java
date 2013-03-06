/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

import com.abada.esper.service.EsperService;
import com.abada.eva.action.dimex.DimexAction;
import java.util.Map;

/**
 *
 * @author jesus
 */
public class SepsisSyndromeAction implements DimexAction<Boolean> {

    private EsperService cep;
    
    
    public void doIt(Boolean resultFromDimex, Map<String, Object> data) {
        
        SepsisSyndrome ss = new SepsisSyndrome(resultFromDimex, data);
        
       /* if(cep.canSend()){
            //cep.
        }*/
        
        System.out.println("\n\n\n\n\n El resultado es: "+ resultFromDimex+"\n\n\n\n");
        
    }
    
    
    
}
