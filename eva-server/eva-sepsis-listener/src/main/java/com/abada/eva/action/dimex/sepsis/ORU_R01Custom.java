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


import java.util.Map;

/**
 *
 * @author jesus
 */
public class ORU_R01Custom {
    
    private String messageId;
    private String nhc;
    private boolean hemocultivo;
    private Map<String,Object> symptons;

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setNhc(String nhc) {
        this.nhc = nhc;
    }

    public void setHemocultivo(boolean hemocultivo) {
        this.hemocultivo = hemocultivo;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getNhc() {
        return nhc;
    }

    public boolean isHemocultivo() {
        return hemocultivo;
    }

    public Map<String, Object> getSymptons() {
        return symptons;
    }

    public void setSymptons(Map<String, Object> symptons) {
        this.symptons = symptons;
    }

    
}
