package com.sampleProject.bookMyShowApp.response;

public class UserResponse {
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    String name;
    Integer age;
    String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserResponse(String name, Integer age, String city, Long id) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.id=id;
    }
}
