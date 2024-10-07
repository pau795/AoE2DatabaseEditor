package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.TauntView;

public class TauntElement extends Editable{

    public TauntElement(int id) {
        super(id);
    }

    @Override
    public String getName(){
        if (id == 0) return "none";
        return String.format("taunt_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.TAUNT;
    }

    @Override
    public EditableView getEditableView() {
        return new TauntView(this);
    }

    public static TauntElement getNone(){
        return new TauntElement(0);
    }
}
