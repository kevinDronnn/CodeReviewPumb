package com.example.codereviewpumb.Dto;

import java.util.Objects;

public class AnimalDto {
    private String name;

    private String type;

    private String sex;

    private byte weight;

    private byte cost;

    private byte category;

    public AnimalDto() {
    }

    public AnimalDto(String name, String type, String sex, byte weight, byte cost) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.weight = weight;
        this.cost = cost;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDto animalDto = (AnimalDto) o;
        return weight == animalDto.weight &&
                cost == animalDto.cost &&
                category == animalDto.category &&
                Objects.equals(name, animalDto.name) &&
                Objects.equals(type, animalDto.type) &&
                Objects.equals(sex, animalDto.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, sex, weight, cost, category);
    }

    @Override
    public String toString() {
        return "AnimalDto{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sex='" + sex + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", category=" + category +
                '}';
    }
}
