/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.service;

import ca.uhn.hl7v2.model.Message;
import com.abada.esper.EsperLoader;
import com.abada.esper.configuration.model.Statement;
import com.abada.esper.configuration.model.Statements;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;
import com.thoughtworks.xstream.XStream;
import java.net.URL;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author mmartin
 */
public class EsperService {

    private EsperLoader loader;
    private EPRuntime runtime;

    public EsperService() {
    }

    public EsperService(URL url, EsperLoader loader) throws Exception {
        Statements statements = this.getConfiguration(url);
        this.loader = loader;
        this.runtime = this.loader.getEPRuntime();
        this.loadStatements(statements);
    }

    public void send(Message message) {

        runtime.sendEvent(message);

    }

    private Statements getConfiguration(URL url) {
        XStream x = new XStream();
        x.processAnnotations(Statements.class);
        Statements s = (Statements) x.fromXML(url);

        return s;
    }

    private void loadStatements(Statements statements) throws Exception {

        List<Statement> ls = statements.getStatements();
        EPStatement stmt = null;
        for (Statement s : ls) {
            EPAdministrator adm = loader.getEPAdministrator();
            stmt = adm.createEPL(s.getEPL());

            for (String l : s.getListeners()) {
                stmt.addListener(this.createListener(l));
            }
            stmt.start();
            //TODO add subscribers
        }

    }

    private UpdateListener createListener(String l) throws Exception {

        UpdateListener listener = (UpdateListener) Class.forName(l).newInstance();

        return listener;

    }
}
