package com.auto.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_brand")
    private Brand brand;

    @Column(name = "model_name")
    private String name;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Collection<Auto> autos = new ArrayList<Auto>();

    public Model(Brand brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public Model() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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

    public void setAutos(Collection<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
