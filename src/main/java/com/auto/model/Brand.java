package com.auto.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private long id;

    @Column(name = "brand_name")
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Collection<Auto> autos = new ArrayList<Auto>();

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Collection<Model> models = new ArrayList<Model>();

    public Brand(String name) {
        this.name = name;
    }

    public Brand() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Auto> getAutos() {
        return autos;
    }

    public Collection<Model> getModels() {
        return models;
    }

    public void setModels(Collection<Model> models) {
        this.models = models;
    }

    public void setAutos(Collection<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
