/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.historic.entities;

import ca.uhn.hl7v2.model.Message;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmartin
 */
@Entity
@Table(name = "historicaction")
@XmlRootElement
/**
 * Entity with all information about an Statement that is executed
 */
public class HistoricAction implements Serializable {

    @Id
    private String id;
    @Column(length = 255, nullable = false)
    private String EPLName;
    @Column(nullable = false)
    @Lob
    private String epl;
    @Column(nullable = false)
    @Lob
    private Message[] newEvents;
    @Column(nullable = true)
    @Lob
    private Message[] oldEvents;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date run;

    public HistoricAction() {
    }

    public HistoricAction(String id, String EPLName, String epl, Message[] newEvents, Message[] oldEvents, Date run) {
        this.id = id;
        this.EPLName = EPLName;
        this.epl = epl;
        this.newEvents = newEvents;
        this.oldEvents = oldEvents;
        this.run = run;
    }

    public String getEPLName() {
        return EPLName;
    }

    public void setEPLName(String EPLName) {
        this.EPLName = EPLName;
    }

    public String getEpl() {
        return epl;
    }

    public void setEpl(String epl) {
        this.epl = epl;
    }

    public Message[] getNewEvents() {
        return newEvents;
    }

    public void setNewEvents(Message[] newEvents) {
        this.newEvents = newEvents;
    }

    public Message[] getOldEvents() {
        return oldEvents;
    }

    public void setOldEvents(Message[] oldEvents) {
        this.oldEvents = oldEvents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRun() {
        return run;
    }

    public void setRun(Date run) {
        this.run = run;
    }
}
