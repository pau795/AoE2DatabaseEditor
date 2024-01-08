package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ImageEditor extends HorizontalLayout {
    
    public ImageEditor(ImageEditable editable){
        Image icon = new Image();
        icon.setSrc(Database.getImage(editable.getImagePath()));
        icon.setClassName("editable-label-icon");

        TextField imageField = new TextField();
        imageField.setValue(editable.getImagePath());
        imageField.addValueChangeListener(e ->{
            editable.setImagePath(e.getValue());
            imageField.setValue(e.getValue());
            icon.setSrc(Database.getImage(e.getValue()));
        });
        setAlignItems(Alignment.CENTER);
        setPadding(true);
        add(icon, imageField);
    }
}
