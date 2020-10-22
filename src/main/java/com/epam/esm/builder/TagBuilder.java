package com.epam.esm.builder;

import com.epam.esm.entity.Tag;

public class TagBuilder {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public TagBuilder buildWithId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagBuilder buildWithName(String name) {
        this.name = name;
        return this;
    }

    public Tag buildTag(){
        return new Tag(this);
    }

}