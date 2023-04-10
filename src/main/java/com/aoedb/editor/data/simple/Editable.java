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
        String name2;
        String name1 = Database.getString(this.getName());
        if (name1.length() < 30) name2 = name1;
        else name2 = name1.substring(0, 27) + "...";
        Label name = new Label(name2);
        name.getElement().setAttribute("title", Database.getString(this.getName()));
        name.addClassNames("editable-label-name");
        layout.add(name);
        return layout;
    }

}
