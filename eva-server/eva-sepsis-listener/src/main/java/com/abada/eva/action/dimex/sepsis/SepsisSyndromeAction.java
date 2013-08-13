/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex.sepsis;

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
