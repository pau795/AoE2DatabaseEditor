package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;

public class TypeElement extends ImageEditable {


    public TypeElement(int id) {
        super(id);
    }

    @Override
    public String getName(){
        return String.format("type_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.TYPE;
    }

}
