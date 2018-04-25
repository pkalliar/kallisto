package com.pankal.protocol;


import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name="protocols_v", schema="comm")
public class ProtocolView {

    @Id
    private String name;

    private String params;

    public String getName() {
        return name;
    }

    public String[] getParams() {
        return params.split(",");
    }
}
