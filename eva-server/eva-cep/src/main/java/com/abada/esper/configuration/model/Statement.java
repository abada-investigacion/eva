/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.configuration.model;

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

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *  Class for marshall and unmarshall the xml configuration file for EVA
 * @author mmartin
 */
public class Statement {
    @XStreamAsAttribute
    private String name;
    //@XStreamImplicit(itemFieldName="listener")
    //private List<String> listeners;
    private String EPL;
    private String springContext;
    //@XStreamImplicit(itemFieldName="subscriber")
    //private List<String> subscribers;

    public String getEPL() {
        return EPL;
    }

    public void setEPL(String EPL) {
        this.EPL = EPL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpringContext() {
        return springContext;
    }

    public void setSpringContext(String springContext) {
        this.springContext = springContext;
    }

}
