package Homeworks.day3.task01;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojoTask01 {
    /*
    POJO = plain Old Java Object ----> Perfect template to create instances

    1.Create private variables for each field
    2.Create constructors with parameters and without parameters
    3.Create Getters and Setters
    4.Create ToString
    */

    //Create private variables for each field
    private String name;
    private String job;

    //Create constructors with parameters and without parameters
    public JsonPlaceHolderPojoTask01(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public JsonPlaceHolderPojoTask01() {
    }
    //Create Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    //Create ToString
    @Override
    public String toString() {
        return "JsonPlaceHolderPojoHw3{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
