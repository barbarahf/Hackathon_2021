package com.example.demo.entities;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //TODO: REFACTOR
    @ElementCollection
    private Map<String, String> alphaCode; //List of lists
    @ElementCollection
    private List<String> callingCode; //List

    public Country() {

    }

    // <editor-fold defaultstate="collapsed" desc=" GETTERS AND SETTERS ">
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
    // </editor-fold>
}
