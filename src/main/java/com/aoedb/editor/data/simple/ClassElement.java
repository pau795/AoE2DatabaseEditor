package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;

public class ClassElement extends ImageEditable{

    public ClassElement(int id) {
        super(id);
    }

    @Override
    public String getName(){
        return String.format("class_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.CLASS;
    }
}
