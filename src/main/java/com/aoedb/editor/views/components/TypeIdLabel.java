package com.aoedb.editor.views.components;

import com.aoedb.editor.data.simple.Editable;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class TypeIdLabel extends HorizontalLayout {
    public TypeIdLabel(Editable e){
        String id =  String.valueOf(e.getId());
        String type = e.getType();
        setPadding(true);
        add(new Label(String.format("ID: %s - Type: %s",id, type)));
    }
}
