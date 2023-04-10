package com.aoedb.editor.views;

import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.database.UndoRedoUtility;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;


@RoutePrefix("editable")
@Route(value = ":type?/:ID?", layout = MainLayout.class)
@CssImport(value = "./themes/aoe2databaseeditor/components/editable-component.css")
public class EditableView extends VerticalLayout implements BeforeEnterObserver {

    protected RouteParameters parameters;
    String type;
    int editableID;
    private Editable editable;

    public void setupView(){
        HorizontalLayout idLayout = new HorizontalLayout();
        ImageEditable ime = (ImageEditable) editable;
        Image icon = new Image();
        icon.setSrc(Database.getImage(ime.getImagePath()));
        icon.setClassName("editable-label-icon");

        Label idLabel =  new Label("ID - " + ime.getId());
        Label nameLabel =  new Label(Database.getString(ime.getName()));
        TextField imageField = new TextField();
        imageField.setValue(ime.getImagePath());
        imageField.addValueChangeListener(e ->{
            UndoRedoUtility uru = UndoRedoUtility.getInstance();
            if (!uru.isActionInProgress()) {
                ime.setImagePath(e.getValue());
                imageField.setValue(e.getValue());
                icon.setSrc(Database.getImage(e.getValue()));
                uru.pushUndo(() -> {
                    ime.setImagePath(e.getOldValue());
                    imageField.setValue(e.getOldValue());
                    icon.setSrc(Database.getImage(e.getOldValue()));
                });
            }
        });
        idLayout.add(icon, idLabel, nameLabel, imageField);
        this.add(idLayout);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        this.parameters = beforeEnterEvent.getRouteParameters();
        this.type = parameters.get("type").orElse("");
        this.editableID = Integer.parseInt(parameters.get("ID").orElse("-1"));
        this.editable = Database.getEditable(type, editableID);
        this.setupView();
    }


}
