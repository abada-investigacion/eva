/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.action.dimex;

import java.util.Map;

/**
 *
 * @author katsu
 */
public interface DimexAction<T> {
    public void doIt(T resultFromDimex, Map<String,Object> data);
}
