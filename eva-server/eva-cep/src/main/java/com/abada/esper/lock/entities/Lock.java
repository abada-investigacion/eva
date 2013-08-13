/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.esper.lock.entities;

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

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity for lock
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
    @Column(name = "start1")
    private Long start;
    //@Column(name = "end1")
    @Basic(optional=true)
    private Long end;
    @Basic
    //@Column(name = "locked1")
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
