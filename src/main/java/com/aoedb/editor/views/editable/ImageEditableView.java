package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editors.ImageEditor;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;

public abstract class ImageEditableView extends EditableView {

    private final ImageEditable imageEditable;

    public ImageEditableView(ImageEditable ie){
        super(ie);

        this.imageEditable = ie;
    }

    @Override
    public Div getView(){
        Div layout = super.getView();
        ImageEditor imageEditor = new ImageEditor(imageEditable);
        Accordion imageAccordion = new Accordion();
        imageAccordion.add("Image", imageEditor);
        layout.add(imageAccordion);
        return layout;
    }

    @Override
    public Div getLabel(){
        Div layout = super.getLabel();
        Image icon = new Image();
        icon.setSrc(Database.getImage(this.imageEditable.getImagePath()));
        icon.addClassNames("editable-label-icon");
        layout.addComponentAsFirst(icon);
        return layout;
    }

    @Override
    public Component getSearchLabel(){
        Div layout = new Div();
        layout.addClassNames("search-identifier-layout");

        Image icon = new Image();
        icon.setSrc(Database.getImage(this.imageEditable.getImagePath()));
        icon.addClassNames("search-identifier-icon");


        Label name = new Label(Database.getString(this.imageEditable.getName()));
        name.addClassNames("search-identifier-name");

        Label id = new Label("ID - " + this.imageEditable.getId());
        id.addClassNames("search-identifier-id");
        Label type = new Label(this.imageEditable.getType());
        type.addClassNames("search-identifier-type");
        layout.add(icon, name, id, type);
        return layout;
    }

}
