/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abada.selene.v25.message;

import ca.uhn.hl7v2.model.Message;
import com.abada.generator.object.IObject;

/**
 *
 * @author david
 */
public interface IMessage<M extends Message,F extends IObject> {
    public M exec(F object);
}
