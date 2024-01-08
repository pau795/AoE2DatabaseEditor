package com.aoedb.editor.data.simple;


import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

public abstract class ImageEditable extends Editable {

    private String imagePath;
    public ImageEditable(int id) {
        super(id);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
