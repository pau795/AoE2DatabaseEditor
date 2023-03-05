package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

import java.io.Serializable;

public abstract class Editable implements Serializable {

    protected Integer id;


    public Editable(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getName();

    public abstract String getType();

    public Component getEditableLabel(){
        Div layout =  new Div();
        layout.addClassNames("editable-label-layout");
        Label name = new Label(Database.getString(this.getName()));
        name.addClassNames("editable-label-name");

        Label id = new Label("ID - " + this.getId());
        id.addClassNames("editable-label-id");
        Label type = new Label(this.getType());
        type.addClassNames("editable-label-type");
        layout.add(name, id, type);
        return layout;
    }

}
