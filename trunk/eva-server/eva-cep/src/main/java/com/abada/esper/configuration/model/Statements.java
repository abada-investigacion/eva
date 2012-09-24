/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.configuration.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 * Class for marshall and unmarshall the xml configuration file for EVA
 * @author mmartin
 */
@XStreamAlias("statements")
public class Statements {
    @XStreamImplicit(itemFieldName="statement")
    private List<Statement> statements;

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
    
}
