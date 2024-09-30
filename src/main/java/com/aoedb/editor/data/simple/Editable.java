package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

import java.io.Serializable;

public abstract class Editable implements Serializable {

    protected Integer id;


    public Editable(int id){
        this.id = id;
    }

    public Editable(int id, Editable mainEditable){
        this.id = id;
        Database.setString(String.format("%s_name_%d", this.getType(), this.id), Database.getString(mainEditable.getName(), "en"), "en");
        Database.setString(String.format("%s_name_%d",this.getType(), this.id), Database.getString(mainEditable.getName(), "es"), "es");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getName();

    public abstract String getType();

    public abstract EditableView getEditableView();

}
