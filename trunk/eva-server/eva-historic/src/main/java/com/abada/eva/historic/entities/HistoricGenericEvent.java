/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.eva.historic.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmartin
 */
@Entity
@Table(name = "historicGeneric")
@XmlRootElement
/**
 * Entity with all information about an HL7 event and it occurs
 */
public class HistoricGenericEvent {

    @Id
    @Column(name = "idHEG")
    private String id;
    @Column(nullable = false)
    @Lob
    @Basic(fetch= FetchType.EAGER)
    private String trace;
    @Column(nullable = false)    
    private String type;
    @Column(nullable = false)
    private Long run;
    @Column(length = 255, nullable = false)
    private String className;
    @Column(length = 255, nullable = false)
    private String principal;

    public HistoricGenericEvent() {
    }

    public HistoricGenericEvent(String id, String trace,String type, Long run, String principal) {
        this.id = id;
        this.trace = trace;
        this.type=type;
        this.run = run;
        this.className = trace.getClass().getName();
        this.principal = principal;
    }   
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Long getRun() {
        return run;
    }

    public void setRun(Long run) {
        this.run = run;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }        
}
