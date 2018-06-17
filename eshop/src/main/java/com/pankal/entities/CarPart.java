package com.pankal.entities;

public class CarPart {

    public int id;
    public int parent_id;
    public String name;
    public String path;
    public boolean has_make;

    public CarPart(int id, int parent_id, String name, String path, boolean has_make) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.path = path;
        this.has_make = has_make;
    }
}
