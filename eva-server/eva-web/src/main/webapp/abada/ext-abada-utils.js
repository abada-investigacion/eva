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
/**
 *is a number
 */
function isNumeric(sText)
{
    var ValidChars = "0123456789.";
    var IsNumber=true;
    var Char;

    for (i = 0; i < sText.length && IsNumber; i++)
    {
        Char = sText.charAt(i);
        if (ValidChars.indexOf(Char) == -1)
        {
            IsNumber = false;
        }
    }
    return IsNumber;
}

/**
 * Set an Ext Component in the center panel
 * @param panelCentral
 * @return
 */
function setCentralPanel(panelCentral,autoDestroy){
    var aux=Ext.getCmp('centralPanel');
    if (aux){
        //aux.suspendEvents();
        if (autoDestroy){
            aux.removeAll(true);
        }
        else{
            aux.removeAll(false);
        }
        aux.add(panelCentral);
        aux.doLayout();
    //aux.resumeEvents();
    }
}

function getRelativeURI(url){
    if (url.substr(0,1) != "/")
        url='/'+url;    
    return window.location.pathname.substr(0,window.location.pathname.indexOf('/',1))+url;
}