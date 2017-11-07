package com.auto.model;

import javax.persistence.*;

@Entity
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue
    @Column(name = "auto_id")
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auto_brand")
    private Brand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auto_model")
    private Model model;

    @Column(name = "auto_year")
    private int year;

    @Column(name = "auto_mileage")
    private int mileage;


    @Column(name = "auto_volume")
    private double volume;

    @Column(name = "auto_cylinders")
    private int cylinders;

    @Column(name = "auto_transmission")
    private String transmission;

    @Column(name = "auto_gears")
    private int gears;

    @Column(name = "auto_fuel")
    private String fuel;

    @Column(name = "auto_drive_train")
    private String driveTrain;

    @Column(name = "auto_color")
    private String color;

    @Column(name = "auto_price")
    private int price;

    @Column(name = "auto_images")
    private int images;

    @Column(name = "auto_info")
    private String info;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auto_user")
    private Users users;

    public Auto(Brand brand, Model model, int year, int mileage, double volume, int cylinders, String transmission, int gears, String fuel, String driveTrain, String color, int price, int images, String info, Users users) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.volume = volume;
        this.cylinders = cylinders;
        this.transmission = transmission;
        this.gears = gears;
        this.fuel = fuel;
        this.driveTrain = driveTrain;
        this.color = color;
        this.price = price;
        this.images = images;
        this.info = info;
        this.users = users;
    }

    public Auto() {

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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", year=" + year +
                ", mileage=" + mileage +
                ", volume=" + volume +
                ", cylinders=" + cylinders +
                ", transmission='" + transmission + '\'' +
                ", gears=" + gears +
                ", fuel='" + fuel + '\'' +
                ", driveTrain='" + driveTrain + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", images=" + images +
                ", info='" + info + '\'' +
                '}';
    }
}

