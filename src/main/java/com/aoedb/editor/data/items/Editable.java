package com.aoedb.editor.data.items;

import java.io.Serializable;

public abstract class Editable implements Serializable {
    protected int id;


    public Editable(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected abstract String getNamePattern();


}
