package com.aoedb.editor.views.components;

import com.vaadin.flow.component.html.Span;

public class Divider extends Span {


    public Divider(Boolean vertical){
        getStyle().set("background-color", "rgba(222, 222, 222, 0.4)");
        this.getStyle().set("align-self", "stretch");
        if (vertical){
            this.getStyle().set("flex", "0 0 1px");
        }
        else{
            this.getStyle().set("height", "1px");
        }
    }

}
