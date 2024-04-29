package com.example.codereviewpumb.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "animals")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "sex")
    private String sex;
    @Column(name = "weight")
    private byte weight;
    @Column(name = "cost")
    private byte cost;
    @Column(name = "category")
    private byte category;

    public AnimalEntity() {
    }

    public AnimalEntity(String name, String type, String sex, byte weight, byte cost, byte category) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.weight = weight;
        this.cost = cost;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public byte getWeight() {
        return weight;
    }

    public void setWeight(byte weight) {
        this.weight = weight;
    }

    public byte getCost() {
        return cost;
    }

    public void setCost(byte cost) {
        this.cost = cost;
    }

    public byte getCategory() {
        return category;
    }

    public void setCategory(byte category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", sex='" + sex + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
