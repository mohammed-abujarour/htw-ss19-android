package de.htwberlin.masterdetails.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mensa implements Serializable {

private String name;
private int id;
private Map<String, String> details = new HashMap<>();

    public Mensa(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addDetails(String key, String value){
        this.details.put(key, value);
    }
    public Map<String, String> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
