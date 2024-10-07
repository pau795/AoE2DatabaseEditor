package com.aoedb.editor.data.simple;

import com.aoedb.editor.data.entity.Unit;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.ClassView;
import com.aoedb.editor.views.editable.EditableView;

public class ClassElement extends ImageEditable{

    public ClassElement(int id) {
        super(id);
    }

    @Override
    public String getName(){
        if (id == 0) return "none";
        return String.format("class_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.CLASS;
    }

    @Override
    public EditableView getEditableView() {
        return new ClassView(this);
    }

    public static ClassElement getNone(){
        ClassElement none = new ClassElement(0);
        none.setImagePath("t_white");
        return none;
    }
}
