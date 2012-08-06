/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jesus
 */
@Entity
@Table(name = "eventlock")
@XmlRootElement
public class Lock {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    @Basic(optional=false)
    private Long start;
    @Basic(optional=true)
    private Long end;
    @Basic
    private Boolean locked;

    public Lock(){
        this.start = System.currentTimeMillis();
        this.locked = true;
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }
    
    
    
}
