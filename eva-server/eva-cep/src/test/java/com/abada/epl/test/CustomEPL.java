package com.abada.epl.test;

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

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.validation.ValidationContext;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mmartin
 */
public class CustomEPL implements Message{

    private String test;
    private Long time;
    
    public CustomEPL(Long time){
        
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
    

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getSomething() {

        return "something";
    }

    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ValidationContext getValidationContext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setValidationContext(ValidationContext theContext) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Character getFieldSeparatorValue() throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getEncodingCharactersValue() throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setParser(Parser parser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Parser getParser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void parse(String string) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String encode() throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Message generateACK() throws HL7Exception, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Message generateACK(String theAcknowldegementCode, HL7Exception theException) throws HL7Exception, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String printStructure() throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Structure[] getAll(String name) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Structure get(String name) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Structure get(String name, int rep) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRequired(String name) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isRepeating(String name) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isGroup(String name) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getNames() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Class getClass(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String addNonstandardSegment(String name) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String addNonstandardSegment(String name, int theIndex) throws HL7Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Message getMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Group getParent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
