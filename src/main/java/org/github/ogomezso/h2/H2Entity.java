package org.github.ogomezso.h2;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class H2Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    protected H2Entity(){}
    public H2Entity(String message) {
        this.message = message;
    }
}
