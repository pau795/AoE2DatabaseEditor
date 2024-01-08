package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.MainLayout;
import com.aoedb.editor.views.editors.StringEditor;
import com.aoedb.editor.views.components.TypeIdLabel;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;


@RoutePrefix("editable")
@Route(value = ":type?/:ID?", layout = MainLayout.class)
@CssImport(value = "./themes/aoe2databaseeditor/components/editable-component.css")
public abstract class EditableView {

    private final Editable editable;

    public EditableView(Editable e){
        this.editable = e;
    }

    public Div getView(){
        Div mainLayout = new Div();
        mainLayout.addClassNames("editable-component-base-layout");
        TypeIdLabel typeIdLabel = new TypeIdLabel(editable);
        Accordion nameAccordion = new Accordion();
        nameAccordion.add("Name", new VerticalLayout(new StringEditor(editable.getName())));
        Accordion idAccordion = new Accordion();
        idAccordion.add("ID", typeIdLabel);

        mainLayout.add(idAccordion, nameAccordion);
        return mainLayout;
    }

    public Div getLabel(){
        Div layout =  new Div();
        layout.addClassNames("editable-label-layout");
        String name1 = Database.getString(this.editable.getName());
        Label name = new Label(name1);
        name.addClassNames("editable-label-name");
        layout.add(name);
        return layout;
    }

    public Component getSearchLabel(){
        Div layout = new Div();
        layout.addClassNames("search-identifier-layout");
        Label name = new Label(Database.getString(this.editable.getName()));
        name.addClassNames("search-identifier-name");

        Label id = new Label("ID - " + this.editable.getId());
        id.addClassNames("search-identifier-id");
        Label type = new Label(this.editable.getType());
        type.addClassNames("search-identifier-type");
        layout.add(name, id, type);
        return layout;
    }





}
