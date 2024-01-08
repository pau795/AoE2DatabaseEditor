package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.TypeView;

import java.lang.reflect.Type;

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

    @Override
    public EditableView getEditableView() {
        return new TypeView(this);
    }

}
