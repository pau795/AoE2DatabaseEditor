package com.aoedb.editor.data.items;


import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.StatView;

public class Stat extends Editable {
    private Boolean addition;

    public Stat(int id) {
        super(id);
    }

    @Override
    public String getName(){
        return String.format("stat_name_%d", this.id);
    }

    public boolean isAddition() {
        return addition;
    }

    public void setAddition(boolean addition) {
        this.addition = addition;
    }

    @Override
    public String getType(){
        return Database.STAT;
    }

    @Override
    public EditableView getEditableView() {
        return new StatView(this);
    }
}
